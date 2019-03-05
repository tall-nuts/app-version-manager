/**
 * options:{"tabName":"","tabTitle":"","tabUrl":""}
 * tabName:当前tab的名称 home
 * tabTitle:当前tab的标题 首页
 * tabUrl:当前tab所指向的URL地址
 */
function addTab(options) {
    let exists = checkTabIsExists(options.tabName);
    if (exists) {
        $("#tab-a-" + options.tabName).click();
    } else {
        //添加tab
        $("#tab-container").append('<li id="tab-li-' + options.tabName + '"><a id="tab-a-' + options.tabName + '" data-toggle="tab" href="#tab-content-' + options.tabName + '"> ' + options.tabTitle + ' <i onclick="closeTab(this)" class="fa fa-remove tab-close"></i></a></li>');
        //添加tab对应的content页
        $("#tab-content").append('<div id="tab-content-' + options.tabName + '" class="tab-pane"></div>');
        //模拟点击，切换当前新建tab
        $("#tab-a-" + options.tabName).click();
        //请求tab内容页数据
        fetchTabContentPage(options.tabUrl, $("#tab-content-" + options.tabName));
    }
}

/**
 * 判断是否存在指定的标签页
 * @param tabName
 * @returns {Boolean}
 */
function checkTabIsExists(tabName) {
    let tab = $("#tab-container" + " > #tab-li-" + tabName);
    return tab.length > 0;
}

/**
 * 请求tab内容页数据
 * @param url
 * @param tabContentDiv
 */
function fetchTabContentPage(url, tabContentDiv) {
    $.ajax({
        type: "GET",
        url: url,
        async: true,
        contentType: "application/json",
        success: function (result) {
            tabContentDiv.html(result);
        },
        error: function (request, status, errorThrown) {
            console.log(status + errorThrown);
        }
    });
}

/**
 * 打开Tab页面
 * @param tabName ID
 * @param tabTitle 标题
 * @param tabUrl 接口相对地址
 */
function openTab(tabName, tabTitle, tabUrl) {
    let options = {"tabName": tabName, "tabTitle": tabTitle, "tabUrl": tabUrl};
    addTab(options)
}

/**
 * Tab关闭
 * @param iElement i标签
 */
function closeTab(iElement) {
    //获取Tab <li></li>标签
    let currentTabLiTag = $(iElement).parent().parent();
    let currentTabContentId = $(iElement).parent().prop("href");
    if (currentTabContentId.indexOf("http") >= 0) {
        //获取的href值会自动拼接主机地址。进行分割获取相对地址
        let noWithSchemeUrl = currentTabContentId.split('//')[1];
        let paths = noWithSchemeUrl.split('/');
        currentTabContentId = paths[paths.length - 1];
    }
    console.log("currentTabContentId:" + currentTabContentId);
    if (currentTabLiTag.hasClass('active')) {
        //如果关闭的tab是当前选中的
        let currentTabUlTag = currentTabLiTag.parent();
        let tabChildrenCount = currentTabUlTag.children().length;
        console.log("currentTabUlTag:" + currentTabUlTag, "tabChildrenCount" + tabChildrenCount);
        //如果标签不止一个，则判断当前标签的位置，如果是最后一个，则前一个选中，否则后一个选中
        if (tabChildrenCount > 1) {
            if (currentTabLiTag.index() == (tabChildrenCount - 1)) {
                //前一个元素选中
                currentTabLiTag.prev().click();
            } else {
                //后一个元素选中
                currentTabLiTag.next().click();
            }
        }
    }
    //移除当前li标签以及其内容
    currentTabLiTag.remove();
    //移除对应content内容
    $(currentTabContentId).remove();
}
