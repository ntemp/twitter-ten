/*global Backbone, jQuery, _ */
var app = app || {};

(function ($) {
    'use strict';

    // The Application
    // ---------------

    // Our overall **AppView** is the top-level piece of UI.
    app.AppView = Backbone.View.extend({
        el: '.tweetapp',

        // Delegated events
        events: {
            'keyup .tweet-filter': 'addAll'
        },

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
            if (!this.isTweetVisible(tweet.get("text"))) {
                return;
            }

            var view = new app.TweetView({ model: tweet });
            this.$list.append(view.render().el);
        },

        // Add all items in the **Tweets** collection at once.
        addAll: function () {
            this.$list.html('');
            app.tweets.each(this.addOne, this);
        },

        isTweetVisible: function (text) {
            var filterValue = this.$filterInput.val()
            if (_.isEmpty(filterValue)) {
                return true;
            }

            return text.search(new RegExp(filterValue.trim(), "i")) !== -1;
        }
    });
})(jQuery);
