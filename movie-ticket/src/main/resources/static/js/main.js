tinymce.init({
    selector: 'textarea', // Chọn các thẻ <textarea> để biến chúng thành trình soạn thảo TinyMCE
    plugins: 'advlist autolink lists link image charmap print preview anchor searchreplace visualblocks code fullscreen insertdatetime media table paste code help wordcount',
    toolbar: 'undo redo | formatselect | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | outdent indent | numlist bullist | link image media | removeformat | code',
    images_file_types: 'jpg,png,svg,webp',
    file_picker_callback: function(callback, value, meta) {
        // Provide file and text for the link dialog
        if (meta.filetype == 'file') {
            callback('mypage.html', {text: 'My text'});
        }

        // Provide image and alt text for the image dialog
        if (meta.filetype == 'image') {
            callback('myimage.jpg', {alt: 'My alt text'});
        }

        // Provide alternative source and posted for the media dialog
        if (meta.filetype == 'media') {
            callback('movie.mp4', {source2: 'alt.ogg', poster: 'image.jpg'});
        }
    }
});