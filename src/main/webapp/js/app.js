/**
 * app.js
 *
 * @author Sachin Handiekar
 */


// Websocket URL
var webSocketURL = 'ws://' + window.location.host + '/insta-realtime/tagSubscription';

// Instagram Client Id
var igClientId = 'Your Instagram Client ID';

console.log('WebSockets Url : ' + webSocketURL);
var webSocket = new WebSocket(webSocketURL);

// Variable to hold the timer object
var pingTimer;

// Ping Timer Interval : 30 seconds
var pingTimerInterval = 30000;

// Ping Message
var PING_MSG = 'PING';

// Demo Hashtag
var demoHashTag = 'jinstagram';


// A boolean variable to hold whether the client was displayed for the first time;
// If it's first time, load some recent images with the #tag provided.
var firstShow = false;

/**
 * Websocket onOpen event
 *
 * @param event
 */
webSocket.onopen = function (event) {
    console.log('WebSocket connection started...');

    pingTimer = setInterval(function () {
        webSocket.send(PING_MSG);
    }, pingTimerInterval);


    if (firstShow == false) {

        $('.instagram').instagram({
            hash: demoHashTag,
            count: 10,
            clientId: igClientId
        });

        firstShow = true;

    }
};

/**
 * Websocket onClose event
 *
 * @param event
 */
webSocket.onclose = function (event) {
    console.log("Remote host closed or refused WebSocket connection");
    console.log(event);
};

/**
 * OnMessage Event
 *
 * @param event
 */
webSocket.onmessage = function (event) {
    console.log(event.data);

    setTimeout(function () {
        $('.instagram').instagram({
            hash: event.data,
            count: 1,
            clientId: igClientId
        });
    }, 2000);


};


$('.instagram').on('didLoadInstagram', loadInstagramPhotos);


function createIGPhotoHolder(photo) {
    var innerHtml = $('<img>')
        .addClass('instagram-image')
        .attr('src', photo.images.thumbnail.url);

    innerHtml = $('<a>')
        .attr('target', '_blank')
        .attr('href', photo.link)
        .append(innerHtml);

    return $('<div>')
        .addClass('instagram-placeholder')
        .attr('id', photo.id)
        .append(innerHtml);
}

function loadInstagramPhotos(event, response) {
    var that = this;

    $.each(response.data, function (i, photo) {
        $(that).append(createIGPhotoHolder(photo));
    });
}

