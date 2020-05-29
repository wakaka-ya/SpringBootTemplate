
// 通用js和css
(function (){
    let strFullPath=window.document.location.href;
    let strPath=window.document.location.pathname;
    let pos=strFullPath.lastIndexOf(strPath);
    let prePath=strFullPath.substring(0,pos);
    let postPath=strPath.substring(0,strPath.substr(1).lastIndexOf('/')+1);
    let appRootPath = prePath+postPath;
    let jsHeader = '<script type="text/javascript" src="' + appRootPath + '/';
    let jsFooter = '"/>';

    console.log(jsHeader + 'static/js/jquery-1.11.3.js' + jsFooter)
    document.write(jsHeader + 'static/js/jquery-1.11.3.js' + jsFooter);
    document.write(jsHeader + 'static/layui/layui.js' + jsFooter);
    document.write(jsHeader + 'static/js/common.js' + jsFooter);
    document.write(jsHeader + 'static/js/jquery.cookie.js' + jsFooter);
    let cssHeader = '<link rel="stylesheet" type="text/css" href="' + appRootPath + '/';
    let cssFooter = '"/>';
    console.log(cssHeader + 'static/layui/css/layui.css' + cssFooter)
    document.write(cssHeader + 'static/layui/css/layui.css' + cssFooter);
    document.write(cssHeader + 'static/lau/lau.css' + cssFooter);
})();