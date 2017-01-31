/**
 * Created by kvasa on 01.01.2017.
 */
$(document).ready(function () {
    $('body').show();
    $('.version').text(NProgress.version);
    NProgress.start();
    setTimeout(function() { NProgress.done(); $('.fade').removeClass('out'); }, 1000);
});