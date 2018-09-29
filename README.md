# BaseApplication

一,MVP模块

特点：
1，根据注解自动生成对应的presenter
2，网络请求自动绑定activity,fragment生命周期，不产生内存泄露。
3，自动切换加载，错误，无数据，有数据四种页面状态，并可以根据需求灵活定制。

1，简单页面不使用MVP模式
xxxActivity extends BaseUIActivity

2,使用MVP模式，不新建Presenter
xxxActivity extends BaseUIActivity<BasePresenter>

basePresenter.subscribe(xxxObservable, new ResponseListener<BaseResponse<xxxEntity>>() {
            @Override
            public void onSuccess(BaseResponse<xxxEntity> userEntityBaseResponse) {

            }

            @Override
            public boolean handleError(NetError netError) {
                return false;
            }
        });

3,使用MVP模式，建立单独的Presenter, 不建新建View
xxxActivity extends BaseUIActivity<xxxPresenter>

xxxPresenter extends BasePresenter<xxxActivity>

4,使用MVP模式，建立单独的Presenter, 建立单独的View
xxxActivity extends BaseUIActivity<xxxPresenter> implements xxxView

xxxPresenter extends BasePresenter<xxxView>

xxxView extends IView

如果发现bug或者有更好的建议请联系我
QQ:359740014 备注Github

