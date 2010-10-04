$().ready(function() {
    $('textarea.tinymce').tinymce({
        // Location of TinyMCE script
        //script_url : 'jscripts/tiny_mce/tiny_mce.js',

        // General options
        theme : "advanced",
        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

        // Theme options
        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleprops,styleselect,formatselect,fontselect,fontsizeselect,visualchars,nonbreaking,|,fullscreen",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,|,search,|,bullist,numlist,|,outdent,indent,|,cite,abbr,acronym,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,code,|,preview,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,iespell,|,ltr,rtl,insertlayer,moveforward,movebackward,absolute",
        theme_advanced_buttons4 : "",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : true,

        // Example content CSS (should be your site CSS)
        content_css : "css/styling.css"
    });
});