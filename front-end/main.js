let sock = new SockJS("http://localhost:8080/stomp");
let client = Stomp.over(sock);
client.connect({'username': 'Jimbob', 'password': 'pass'}, frame => {
  client.subscribe("/topic/messages", payload => {

    let message_list = document.getElementById('message-list');
    let message = document.createElement('li');
    message.appendChild(document.createTextNode(JSON.parse(payload.body).message));
    message_list.appendChild(message);

  });
});

function sendMessage(){

  let input = document.getElementById("message-input");
  let message = input.value;

  client.send('/app/chat', {}, JSON.stringify({message: message}));

}
