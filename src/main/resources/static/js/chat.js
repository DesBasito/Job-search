async function fetchMessages() {
    try {
        let response;
        console.log(messageSize)
        if (messageSize>0){
            response = await fetch(`api/message/${respId}?lastMessageId=${messageSize}`);
        }
        else {
            response = await fetch(`api/message/${respId}?lastMessageId=0`);
        }
        const messages = await response.json();
        console.log(messages)
        displayMessages(messages);
    } catch (error) {
        console.error('Error fetching messages:', error);
    }
}

async function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value;

    try {
        await fetch(`api/message/`+respId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ text: message })
        });
        messageInput.value = ''; // Clear input after sending
        await fetchMessages(); // Refresh messages after sending
    } catch (error) {
        console.error('Error sending message:', error);
    }
}

function displayMessages(messages) {
    const chatBox = document.getElementById('chatBox');
    chatBox.innerHTML = '';
    messages.forEach(message => {
        const messageElement = document.createElement('div');
        messageElement.textContent = `${message.senderEmail}: ${message.content}`;
        chatBox.appendChild(messageElement);
    });
}

window.onload = fetchMessages();