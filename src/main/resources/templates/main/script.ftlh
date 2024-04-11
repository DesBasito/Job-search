<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        #container {
            position: fixed;
            top: 20px;
            right: 20px;
            padding: 10px;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.3);
        }
    </style>
</head>
<body>
<div id="container">
    <input id="add-item-btn" type="button" value="+">
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        let container = document.getElementById('container');
        let addButton = document.getElementById('add-item-btn');


        function createNotification() {
            const notification = document.createElement('div');
            notification.className = 'notification';

            const text = document.createTextNode('Новое уведомление');
            notification.appendChild(text);

            const closeButton = document.createElement('button');
            closeButton.innerText = 'Закрыть';
            closeButton.addEventListener('click', () => {
                container.removeChild(notification);
            });
            notification.appendChild(closeButton);

            container.appendChild(notification);

            setTimeout(() => {
                container.removeChild(notification);
            }, 5000);
        }

        addButton.addEventListener('click', createNotification);
    });
</script>

</body>
</html>
