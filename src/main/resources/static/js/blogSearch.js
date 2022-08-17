$(document).ready(function(){
    
    $("#search").click(function(){
        var quickSearch = $("#quick-search").val();
        quickSearch = quickSearch.replace('#', '');
        var dateMin = $("#date-min").val();
        var dateMax = $("#date-max").val();
        console.log(role);
        var url = "https://my-cms-project-y.herokuapp.com/api/searchBlogs?";
     
        url +=(quickSearch !== '') ? "quickSearch="+quickSearch : '';
        url +=(dateMin !== '') ? "&dateMin="+dateMin : '';
        url +=(dateMax !== '') ? "&dateMax="+dateMax : '';
            
        console.log(dateMin);
        console.log(dateMax);
        console.log(url);
        
        $.ajax({
            type: 'GET',
            url: url,
            success:function(blogsArray) {
                //TO DO: CLEAN THIS
                var role = $("#role").val();
                var searchedBlogDiv = $('#searchedBlogs');
                searchedBlogDiv.replaceWith('<div id="searchedBlogs" style="margine-top:3em;"></div>');
                searchedBlogDiv = $('#searchedBlogs');
                $.each(blogsArray, function(index, blog) {
//                    var editButton = "<div><a href=\"/adminEditBlog?id="+  blog.id + "\" class=\"link\"> Edit </a></div>";
                    var blogInfo = '<div class="card card-animation" style="margin-top:3em;width: 722px; height:480; flex: 0 1 auto;">';
                    blogInfo += '<div style="width: 720px; height: 480px;">';
                    blogInfo += "<img src=\"/images/blog-" + blog.id + "\" style=\" width: 100%; height: 100%; object-fit: cover;\" onerror=\"this.src=\'/images/placeholder.jpg\';\">";  
                    blogInfo += "</div>";
                    blogInfo += "<div class=\"card-body\">";
                    
                    blogInfo += "<h5 class=\"card-title\">"+blog.title+"</h5>";  
                    blogInfo += "<p class=\"card-text\" > "+ blog.description + "</p>";  
                    blogInfo += " <a href=\"/getBlog?id="+  blog.id + "\" class=\"link\"></a>";  
                    
                    
                    var hashtag = "";
                    blog.hashtags.forEach(function(item, index){
                        hashtag += item.name + " "; 
                    });
                    
                    blogInfo += " <span class=\"hashtag\">"+hashtag+"</span>";  
//                    blogInfo += editButton;  
                    
                    blogInfo += "</div></div>";  
            searchedBlogDiv.append(blogInfo);
            });
            },
            error: function(result) {
                alert('error');
            } 
        });
    });
});

