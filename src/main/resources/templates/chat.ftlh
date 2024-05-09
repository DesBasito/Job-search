<#import "layout.ftlh" as main>
<@main.layout>
    <style>
        main {
            padding: 20px;
        }

        .chat-container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: scroll;
        }

        .chat-message {
            max-height: 300px;
            background-color: #e9ecef;
            border-radius: 12px;
            padding: 8px 12px;
            margin-bottom: 8px;
            overflow-y: auto;

            max-width: 70%;
        }

        .chat-input {
            display: flex;
            align-items: center;
            border-top: 1px solid #ced4da;
            padding: 10px;
        }

        .chat-input input {
            flex: 1;
            padding: 8px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            margin-right: 8px;
        }

        .chat-input button {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            background-color: #007bff;
            color: #ffffff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .chat-input button:hover {
            background-color: #0056b3;
        }
    </style>
<main>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="chat-container">
                    <div class="chatBox py-5" id="chatMessages">
                        <#list  messages as message>
                            <div class="chat-message">${message.senderEmail}: ${message.content}</div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <div class="chat-input">
            <input type="text" id="messageInput" placeholder="Type a message...">
            <button class="btn btn-primary btn-send" type="submit">Send</button>
        </div>
    </div>
</main>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="/js/chat.js/">
</script>
<script>
    let lastMessageNum = ${messages?size};
</script>
</@main.layout>