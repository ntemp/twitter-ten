/*global Backbone */
var app = app || {};

(function () {
	'use strict';

	// Tweet Router
	// ----------
	var TodoRouter = Backbone.Router.extend({
		routes: {
			'*filter': 'setFilter'
		},

		setFilter: function (param) {
			// Set the current filter to be used
			app.TodoFilter = param || '';

			// Trigger a collection filter event, causing hiding/unhiding
			// of Todo view items
			app.tweets.trigger('filter');
		}
	});

	app.TweetRouter = new TweetRouter();
	Backbone.history.start();
})();
