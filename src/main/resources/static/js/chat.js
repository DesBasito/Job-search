const path = window.location.pathname;
const segments = path.split('/');
const id = segments[segments.length - 1];
const csrfHeader = document.querySelector("meta[name='_csrf_header']").content;
const csrfToken = document.querySelector("meta[name='_csrf_token']").content;
const headers = {};
headers[csrfHeader] = csrfToken;
let lastNum = lastMessageNum;

async function fetchMessages() {
    try {
        let response;
        const getMessagesUrl = `/api/message/${id}?lastMessageId=`
        response = await fetch(`${getMessagesUrl}${lastNum}`)
        const messages = await response.json();
        displayMessages(messages);
    } catch (error) {
        console.error('Error fetching messages:', error);
    }
}

async function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value;
    try {
       const urlFetch = await fetch(`/api/message`, {
            method: 'POST',
            headers: {
                ...headers,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                respApplId: id,
                content: message,
            })
        });
       messageInput.value = '';
       if (urlFetch.ok){
           await fetchMessages();
           lastNum++;
       }
    } catch (error) {
        lastNum--;
        console.error('Error sending message:', error);
    }
}

function displayMessages(messages) {
    const chatBox = document.getElementById('chatMessages');
    const shouldScrollToBottom = chatBox.scrollHeight - chatBox.scrollTop === chatBox.clientHeight;

    messages.forEach(message => {
        const messageElement = document.createElement('div');
        messageElement.classList.add('chat-message');
        messageElement.textContent = `${message.senderEmail}: ${message.content}`;
        chatBox.appendChild(messageElement);
        lastNum++;
    });

    if (shouldScrollToBottom) {
        chatBox.scrollTop = chatBox.scrollHeight;
    }
}

window.onload = () => {
    fetchMessages();
    setInterval(fetchMessages, 7000);
};

const sendButton = document.querySelector('.btn-send');
sendButton.addEventListener('click', sendMessage);