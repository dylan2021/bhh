package com.android.baihuahu.act_main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.baihuahu.App;
import com.android.baihuahu.R;
import com.android.baihuahu.act_other.BaseFgActivity;
import com.android.baihuahu.bean.DeptInfo;
import com.android.baihuahu.bean.BillInfo;
import com.android.baihuahu.core.net.GsonRequest;
import com.android.baihuahu.core.utils.Constant;
import com.android.baihuahu.core.utils.KeyConst;
import com.android.baihuahu.core.utils.NetUtil;
import com.android.baihuahu.core.utils.TextUtil;
import com.android.baihuahu.util.DialogUtils;
import com.android.baihuahu.util.ToastUtil;
import com.android.baihuahu.util.Utils;
import com.android.baihuahu.view.ExRadioGroup;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dylan
 */

@SuppressLint("WrongConstant")
public class BillListActivity extends BaseFgActivity {
    public String TAG = BillListActivity.class.getSimpleName();
    private ListView listview;
    private BillListAdapter adapter;
    private int TYPE, id;
    private List<BillInfo> applisList = new ArrayList<>();
    private BillListActivity context;
    private RefreshLayout mRefreshLayout;
    private long startTimeL, endTimeL;
    private ExRadioGroup applyRg;
    private String TYPE_CODE = "";
    private View titleLayout;
    private EditText searchEt;
    private TextView cancleBt;
    private TextView filterTv;
    private List<DeptInfo> billIdTreeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_list);
        initStatusBar();

        context = this;
        initTitleBackBt("施工台账");

        initView();

        getBillTree();//台账章节
    }

    //台账章节
    private void getBillTree() {
        String url = Constant.WEB_SITE + "/biz/bizBill/billTree/" + App.projecId;
        Response.Listener<List<DeptInfo>> successListener = new Response.Listener
                <List<DeptInfo>>() {
            @Override
            public void onResponse(List<DeptInfo> billList) {
                mRefreshLayout.finishRefresh(0);
                if (context == null || TextUtil.isEmptyList(billList)) {
                    ToastUtil.show(context, "台账章节数据为空");
                    return;
                }
                billIdTreeList = billList;
                setBillListView();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mRefreshLayout.finishRefresh(0);
            }
        };

        Request<List<DeptInfo>> request = new GsonRequest<List<DeptInfo>>(Request.Method.GET,
                url, successListener, errorListener, new TypeToken<List<DeptInfo>>() {
        }.getType()) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(KeyConst.Authorization, KeyConst.Bearer + App.token);
                params.put(KeyConst.projectId, App.projecId);
                return params;
            }
        };
        App.requestQueue.add(request);
    }

    private void getSearchData(String searchText) {
        if (!NetUtil.isNetworkConnected(context)) {
            mRefreshLayout.finishRefresh(0);
            ToastUtil.show(context, R.string.no_network);
            return;
        }

        String url = Constant.WEB_SITE + "/biz/bizBill/billDetail/report/list" + searchText;

        Response.Listener<List<BillInfo>> successListener = new Response
                .Listener<List<BillInfo>>() {
            @Override
            public void onResponse(List<BillInfo> result) {
                mRefreshLayout.finishRefresh(0);
                adapter.setData(result);
                if (TextUtil.isEmptyList(result)) {
                    ToastUtil.show(context, R.string.no_data);
                    return;
                }
            }
        };

        Request<List<BillInfo>> versionRequest = new
                GsonRequest<List<BillInfo>>(
                        Request.Method.GET, url,
                        successListener, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (null != context && !context.isFinishing()) {
                            mRefreshLayout.finishRefresh(0);
                        }
                        ToastUtil.show(context, R.string.server_exception);
                    }
                }, new TypeToken<List<BillInfo>>() {
                }.getType()) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put(KeyConst.Authorization, KeyConst.Bearer + App.token);
                        params.put(KeyConst.projectId, App.projecId);
                        return params;
                    }
                };
        App.requestQueue.add(versionRequest);
    }

    ArrayList list1 = new ArrayList();
    ArrayList<List<String>> list2 = new ArrayList();
    ArrayList<List<List<String>>> list3 = new ArrayList();

    //设置章节数据
    private void setBillListView() {
        //setTestData();//模拟数据 -测试用
        for (DeptInfo billInfo_1 : billIdTreeList) {
            //第一层
            ArrayList list11 = new ArrayList();
            ArrayList<List<String>> list22 = new ArrayList();
            List<DeptInfo.ChildrenBeanX> children1 = billInfo_1.getChildren();
            if (!TextUtil.isEmptyList(children1)) {
                for (DeptInfo.ChildrenBeanX child : children1) {
                    //第二层
                    list11.add(child.getName());
                    List<String> list33 = new ArrayList();
                    List<DeptInfo.ChildrenBeanX.ChildrenBean> children2 = child.getChildren();
                    if (!TextUtil.isEmptyList(children2)) {
                        for (DeptInfo.ChildrenBeanX.ChildrenBean c2 : children2) {
                            list33.add(c2.getName());
                        }
                    }
                    list22.add(list33);
                }
            } else {
                list11.add("");
                list22.add(new ArrayList<String>());
            }
            list1.add(billInfo_1.getName());
            list2.add(list11);
            list3.add(list22);
        }
    }

    private void initView() {
        filterTv = findViewById(R.id.filter_tv);
        filterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtil.isEmptyList(billIdTreeList)) {
                    ToastUtil.show(context, "台账章节为空");
                    return;
                }
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int index1, int index2, int index3, View v) {
                        String str1 = list1.get(index1).toString();
                        String str2 = "";
                        String str3 = "";
                        if (list2.get(index1) == null) {
                            adapter.setData(null);
                            return;
                        }
                        if (index2 < list2.get(index1).size()) {
                            str2 = "-" + list2.get(index1).get(index2);
                        }
                        if (index2 < list3.get(index1).size()) {
                            if (index3 < list3.get(index1).get(index2).size()) {
                                str3 = "-" + list3.get(index1).get(index2).get(index3);
                            }
                        }

                        filterTv.setText("章节-编号：" + str1 + str2 + str3);
                        //根据章节筛选
                        DeptInfo deptInfo = billIdTreeList.get(index1);
                        if (deptInfo == null || TextUtil.isEmptyList(deptInfo.getChildren())) {
                            ToastUtil.show(context,R.string.no_data);
                            adapter.setData(null);
                            return;
                        }
                        DeptInfo.ChildrenBeanX childrenBean2 = deptInfo.getChildren().get(index2);
                        if (childrenBean2 == null||TextUtil.isEmptyList(childrenBean2.getChildren())) {
                            ToastUtil.show(context,R.string.no_data);
                            adapter.setData(null);
                            return;
                        }
                        int id3 = childrenBean2.getChildren().get(index3).getId();//章节第三层
                        getSearchData("?thirdBillId=" + id3);
                    }
                }).setOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        filterTv.setText("章节-编号：全部");
                        getSearchData("");
                    }
                }).setCancelText("全部").setSubmitColor(ContextCompat.getColor(context, R.color.mainColor))
                        .setCancelColor(ContextCompat.getColor(context, R.color.mainColor)).build();
                pvOptions.setPicker(list1, list2, list3);
                pvOptions.show();

            }
        });

        listview = (ListView) findViewById(R.id.listView);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        mRefreshLayout.autoRefresh(0);
        mRefreshLayout.setPrimaryColors(Color.WHITE);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                searchEt.setText("");
                filterTv.setText("章节-编号：全部");
            }
        });
        Utils.setLoadHeaderFooter(context, mRefreshLayout);//设置头部,底部样式

        adapter = new BillListAdapter(this, applisList);
        listview.setAdapter(adapter);


        searchEt = findViewById(R.id.search_et);
        titleLayout = findViewById(R.id.activity_title_layout);
        cancleBt = findViewById(R.id.search_cancle_bt);
        searchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    cancleBt.setVisibility(View.VISIBLE);
                    cancleBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            searchEt.setText("");
                            searchEt.clearFocus();
                            DialogUtils.hideKeyBorad(context);
                            getSearchData("");
                        }
                    });
                    titleLayout.setVisibility(View.GONE);
                    filterTv.setVisibility(View.GONE);
                } else {
                    cancleBt.setVisibility(View.GONE);
                    titleLayout.setVisibility(View.VISIBLE);
                    filterTv.setVisibility(View.VISIBLE);
                }
            }
        });
        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getSearchData("?name=" + String.valueOf(editable).trim());
                    }
                }, 100);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getSearchData("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        DialogUtils.hideKeyBorad(context);
    }

    //模拟数据
    private void setTestData() {
        /*list1.add("100章");
        list1.add("200章");
        list1.add("300章");  */
        //------------------
/*        ArrayList list11 = new ArrayList();
        list11.add("1001");
        list11.add("1002");
        //----------------
        ArrayList list12 = new ArrayList();
        list12.add("2001");
        list12.add("2003");
        //------------------
        ArrayList list13 = new ArrayList();
        list13.add("3001");


        list2.add(list11);
        list2.add(list12);
        list2.add(list13);
        //-------------------------

        ArrayList<List<String>> list31 = new ArrayList();

        ArrayList list311 = new ArrayList();
        list311.add("a");
        list311.add("b");
        list31.add(list311);

        ArrayList list321 = new ArrayList();
        list321.add("a");
        list321.add("b");
        list321.add("c");
        list31.add(list321);

        ArrayList list331 = new ArrayList();
        list331.add("a");
        list331.add("b");
        list31.add(list331);


        ArrayList<List<String>> list32 = new ArrayList();
        ArrayList list322 = new ArrayList();
        list322.add("a");
        list322.add("b");
        list32.add(list322);

        ArrayList list323 = new ArrayList();
        list323.add("a");
        list323.add("b");
        list32.add(list323);

        ArrayList list324 = new ArrayList();
        list324.add("a");
        list324.add("b");
        list32.add(list324);

        ArrayList<List<String>> list33 = new ArrayList();

        ArrayList list332 = new ArrayList();
        list332.add("a");
        list332.add("b");
        list332.add("c");
        list33.add(list332);

        ArrayList list333 = new ArrayList();
        list333.add("a");
        list333.add("b");

        list33.add(list333);

        ArrayList list334 = new ArrayList();
        list334.add("a");
        list334.add("b");
        list33.add(list334);

        //--------------第2层  个数根据第2层---------------
        list3.add(list31);
        list3.add(list32);
        list3.add(list33);*/
    }
}
