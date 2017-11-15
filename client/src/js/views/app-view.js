/*global Backbone, jQuery, _ */
var app = app || {};

(function ($) {
	'use strict';

	// The Application
	// ---------------

	// Our overall **AppView** is the top-level piece of UI.
	app.AppView = Backbone.View.extend({
		el: '.twitterapp',

		// At initialization we bind to the relevant events on the `Tweets`
		// collection.
		initialize: function () {
			this.$filterInput = this.$('.tweet-filter');
			this.$list = $('.tweet-list');

			this.listenTo(app.tweets, 'sync', this.addAll);
			this.listenTo(app.tweets, 'filter', this.filterAll);

            // Perform the initial fetch
			app.tweets.fetch();
		},

		// Add a single tweet item to the list by creating a view for it, and
		// appending its element to the `<ul>`.
		addOne: function (tweet) {
			var view = new app.TweetView({ model: tweet });
			this.$list.append(view.render().el);
		},

		// Add all items in the **Tweets** collection at once.
		addAll: function () {
		    console.log('add all')
			this.$list.html('');
			app.tweets.each(this.addOne, this);
		},

		filterAll: function () {
			app.tweets.each(this.filterOne, this);
		},

        filterOne: function (tweet) {
            tweet.trigger('visible');
        }
	});
})(jQuery);
