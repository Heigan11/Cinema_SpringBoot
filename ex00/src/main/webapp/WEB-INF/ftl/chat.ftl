<html lang="en">
<head>
    <meta charset="UTF-8">
    <head>
        <style>
            .a {
                padding: 10px 0;
                border-width: 0;
                border-radius: 1em;
                display: block;
                width: 500px;
                height: 60px;
                margin: auto;
                background: #60e6c5;
                color: black;
                font-size: 25px;
                outline: none;
                text-transform: uppercase;
                text-align: center;
            }
            .bubbleWrapper {
                padding: 10px 10px;
                display: flex;
                justify-content: flex-end;
                flex-direction: column;
                align-self: flex-end;
                color: #fff;
                overflow-x: auto;
            }
            .inlineContainer {
                display: inline-flex;
            }
            .inlineContainer.own {
                flex-direction: row-reverse;
            }
            .inlineIcon {
                width:20px;
                object-fit: contain;
            }
            .ownBubble {
                min-width: 60px;
                max-width: 700px;
                padding: 14px 18px;
                margin: 6px 8px;
                background-color: #5b5377;
                border-radius: 16px 16px 0 16px;
                border: 1px solid #443f56;
            }

            .otherBubble {
                min-width: 60px;
                max-width: 700px;
                padding: 14px 18px;
                margin: 6px 8px;
                background-color: #6C8EA4;
                border-radius: 16px 16px 16px 0;
                border: 1px solid #54788e;

            }
            .own {
                align-self: flex-end;
            }
            .other {
                align-self: flex-start;
            }
            span.own,
            span.other{
                font-size: 14px;
                color: grey;
            }

            label.chat {
                font-size: large;
            }

            div.chat {
                height: 50%;
                overflow-x: auto;
                width: 30%;
                margin: auto;
                /*margin-left: 33%;*/
            }

            input {
                font: 1em sans-serif;
                width: 300px;
                box-sizing: border-box;
                border: 1px solid #999;
            }

            input:focus {
                border-color: #000;
            }

            button {
                padding: 10px 0;
                border-width: 0;
                /*display: block;*/
                width: 120px;
                margin: 25px auto 0;
                background: #60e6c5;
                color: black;
                font-size: 14px;
                /*outline: none;*/
                text-transform: uppercase;
            }

        </style>
        <title>Chat WebSocket</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.0/sockjs.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <script type="text/javascript">
            var stompClient = null;

            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility
                    = connected ? 'visible' : 'hidden';
                document.getElementById('response').innerHTML = '';
            }

            function connect() {
                var socket = new SockJS('/ws');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/public', function(message) {
                        showMessageOutput(JSON.parse(message.body));
                    });
                });
            }

            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }

            function sendMessage() {
                var message = {
                    sender: "${user.name}",
                    text: document.getElementById('text').value,
                    movie: { id: ${movie.id}}
                };
                stompClient.send("/app/chat", {}, JSON.stringify(message));
            }

            function printHistory(messageSender, messageText) {

                var messageElement = document.createElement('div');
                messageElement.className = 'bubbleWrapper';

                var container = document.createElement('div');
                if (messageSender === "${user.name}")
                    container.className = 'inlineContainer own';
                else
                    container.className = 'inlineContainer';

                var usernameText = document.createElement('label');
                usernameText.textContent = messageSender;
                if (messageSender === "${user.name}")
                    usernameText.hidden = true;

                var textElement = document.createElement('div');
                if (messageSender === "${user.name}")
                    textElement.className = 'ownBubble own';
                else
                    textElement.className = 'otherBubble other';

                textElement.textContent = messageText;
                messageArea.appendChild(messageElement);
                messageElement.appendChild(container);
                container.appendChild(usernameText);
                container.appendChild(textElement);
            }

            function showMessageOutput(message) {

                var messageElement = document.createElement('div');
                messageElement.className = 'bubbleWrapper';

                var container = document.createElement('div');
                if (message.sender === "${user.name}")
                    container.className = 'inlineContainer own';
                else
                    container.className = 'inlineContainer';

                var usernameText = document.createElement('label');
                usernameText.textContent = message.sender;
                if (message.sender === "${user.name}")
                    usernameText.hidden = true;

                var textElement = document.createElement('div');
                if (message.sender === "${user.name}")
                    textElement.className = 'ownBubble own';
                else
                    textElement.className = 'otherBubble other';

                textElement.textContent = message.text;
                messageArea.appendChild(messageElement);
                messageElement.appendChild(container);
                container.appendChild(usernameText);
                container.appendChild(textElement);
            }
        </script>
</head>
<body onload="disconnect()">
<div class="w3-panel" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px;">
    <div class="a" style="padding: 10px; margin: 10px;">
        ${movie.title}'s CHAT
    </div>
    <div style="padding: 10px; margin: 10px;">
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">
            Disconnect
        </button>
        <button id="profile" onclick="location.href='/profile'">Profile</button>
    </div>
    <div id="conversationDiv" style="display:flex;flex-direction: column;justify-content: center; align-items: center; padding: 10px; margin: 10px;">
        <div id="chat-page" class="chat" style="background-color: #9E9C9C; width: 100%;">
            <div id="messageArea" style="width: 100%;"> </div>
        </div>
        <#list history as message>
        <script>
            printHistory("${message.sender}", "${message.text}");
        </script>
        </#list>
        <br/>
        <div style="padding: 10px; margin: 10px;">
            <input type="text" id="text" placeholder="Write a message..."/>
            <button id="sendMessage" onclick="sendMessage();">Send</button>
        </div>
        <br/>
        <p id="response"></p>
    </div>
</div>
</body>
</html>

