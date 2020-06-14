//js文件
/**********************************************
 * Copyright (C) Corporation. All rights reserved.
 *
 * Author      :  lihaitao
 * Email        : lhtzbj12@126.com
 * Create Date  : 2016-12-26
 * Description  : 表格单元格合并插件
 * Version      : V1.0
 *
 * Revision History:
 *      Date         Author               Description
 *      2016-12-26   lihaitao               create
 *
 ***********************************************
 //调用实例 table 为页面里的表格
 $('table').sdrowpan({
    onAttr: 'data-pid',         //tr的属性名，两行数据中这个属性值相等时才进行合并
    mergeClass: 'doMerge',      //td的类名，当tr的onAttr值相等时，此td进行合并
    notMergeClass: 'notMerge'   //td的类名，当tr的onAttr值相等时，除此td以外其他td合并(**当mergeClass无有效值时才启用**)
});
 */
(function ($) {
    $.fn.sdrowpan = function (options) {
        return this.each(function (index, element) {
            var that = undefined;
            $('tr', this).each(function (trIndex, tr) {
                var tdSelector = 'td';
                if (typeof (options.mergeClass) !== 'undefined' && options.mergeClass.length > 0) {
                    tdSelector += '.' + options.mergeClass;
                } else {
                    tdSelector += ':not(.' + options.notMergeClass + ')';
                }
                if (typeof that !== 'undefined' &&
                    typeof ($(tr).attr(options.onAttr)) !== 'undefined' &&
                    $(tr).attr(options.onAttr) === $(that).attr(options.onAttr)) {
                    var firstTd = $(that).children(tdSelector);
                    if (firstTd.length == 0) {
                        return true;
                    }
                    var rowpan = firstTd.prop('rowspan');
                    rowpan += 1;
                    $(that).children(tdSelector).prop('rowspan', rowpan);
                    $(this).children(tdSelector).hide();
                }
                else {
                    that = tr;
                }
            });

        });
    };
})(jQuery);