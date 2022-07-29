tinymce.init({

    selector: '.mytextarea',

    plugins: [

        'a11ychecker','advlist','advcode','advtable','autolink','checklist','export',

        'lists','link','image','charmap','preview','anchor','searchreplace','visualblocks',

        'powerpaste','fullscreen','formatpainter','insertdatetime','media','table','help','wordcount'

    ],

    toolbar: 'undo redo | formatpainter casechange blocks | bold italic backcolor | ' +

    'alignleft aligncenter alignright alignjustify | ' +

    'bullist numlist checklist outdent indent | removeformat | a11ycheck code table help',
    setup : function(editor) {
      editor.on('init', function(evt) {

      
      // and insert it into the editor
      editor.setContent($('#blogBody').val());


      });
    }
});