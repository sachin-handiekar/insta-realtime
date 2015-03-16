var wsUrl;
if (window.location.protocol == 'http:') {
    wsUrl = 'ws://' + window.location.host + ':8000/websocket-reverse-echo-example/echo';
} else {
    wsUrl = 'wss://' + window.location.host + ':8443/websocket-reverse-echo-example/echo';
}
console.log('WebSockets Url : ' + wsUrl);
var ws = new WebSocket(wsUrl);

ws.onopen = function(event){
    console.log('WebSocket connection started');
};

ws.onclose = function(event){
    console.log("Remote host closed or refused WebSocket connection");
    console.log(event);
};

ws.onmessage = function(event){
    console.log(event.data);
    $("textarea#outputMessage").val(event.data);
};

$("button#messageSubmit").on('click',function(){
    var message = $('textarea#inputMessage').val();
    console.log('Input message .. '+message);
    ws.send(message);
});

