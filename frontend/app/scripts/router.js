Frontend.Router.map(function () {
  
  this.resource('authors', function(){
    this.resource('author', { path: '/:author_id' }, function(){
      this.route('edit');
    });
    this.route('create');
  });
  
  this.resource('books', function(){
    this.resource('book', { path: '/:book_id' }, function(){
      this.route('edit');
    });
    this.route('create');
  });
  
});
