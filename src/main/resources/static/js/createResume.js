document.addEventListener('DOMContentLoaded', function () {
    const workExpContainer = document.getElementById('workExp');
    const educationContainer = document.getElementById('education');

    let workExpCounter = 0;
    let educationCounter = 0;

    function createCard(container) {
        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        if (container === workExpContainer) {
            const workPositionInput = createInputField('text', `workExpInfo[${workExpCounter}].position`,position);
            cardBody.appendChild(workPositionInput);

            const companyNameInput = createInputField('text', `workExpInfo[${workExpCounter}].companyName`,company);
            cardBody.appendChild(companyNameInput);

            const responsibilitiesTextarea = createTextarea(`workExpInfo[${workExpCounter}].responsibilities`,responsibilities);
            cardBody.appendChild(responsibilitiesTextarea);

            const yearsOfWorkInput = createInputField('number', `workExpInfo[${workExpCounter}].years`,years);
            cardBody.appendChild(yearsOfWorkInput);

            workExpCounter++;
        } else if (container === educationContainer) {
            const degreeInput = createInputField('text', `educationInfo[${educationCounter}].degree`,degree);
            cardBody.appendChild(degreeInput);

            const institutionInput = createInputField('text', `educationInfo[${educationCounter}].institution`,institution);
            cardBody.appendChild(institutionInput);

            const programTextarea = createTextarea(`educationInfo[${educationCounter}].program`,program);
            cardBody.appendChild(programTextarea);

            const startDateInput = createInputField('date', `educationInfo[${educationCounter}].startDate`,startDate);
            cardBody.appendChild(startDateInput);

            const endDateInput = createInputField('date', `educationInfo[${educationCounter}].endDate`,endDate);
            cardBody.appendChild(endDateInput);

            educationCounter++;
        }
        let closeButton = document.createElement('button');
        closeButton.className = 'btn btn-danger'
        closeButton.classList.add('card-close');
        closeButton.textContent = remove;
        closeButton.addEventListener('click', () => {
            cardBody.remove();
        });

        cardBody.appendChild(closeButton);

        container.appendChild(cardBody);
        container.appendChild(cardBody);
    }

    function createInputField(type, name, label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        const inputElement = document.createElement('input');
        inputElement.type = type;
        inputElement.name = name;
        inputElement.className = 'form-control';
        container.appendChild(inputElement);

        return container;
    }

    function createTextarea(name,label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        const textareaElement = document.createElement('textarea');
        textareaElement.className = 'form-control';
        textareaElement.name = name;
        container.appendChild(textareaElement);

        return container;
    }

    const addWorkExpBtn = document.getElementById('add-work-btn');
    addWorkExpBtn.addEventListener('click', function () {
        createCard(workExpContainer);
    });

    const addEducationBtn = document.getElementById('add-education-btn');
    addEducationBtn.addEventListener('click', function () {
        createCard(educationContainer);
    });

});