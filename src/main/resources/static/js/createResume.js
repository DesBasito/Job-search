document.addEventListener('DOMContentLoaded', function () {
    const workExpContainer = document.getElementById('workExp');
    const educationContainer = document.getElementById('education');
    const contactsContainer = document.getElementById('addContacts');

    let workExpCounter = 0;
    let educationCounter = 0;

    function createCard(container) {
        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        if (container === workExpContainer) {
            const workPositionInput = createInputField('text', `workExpInfo[${workExpCounter}].position`,'Position');
            cardBody.appendChild(workPositionInput);

            const companyNameInput = createInputField('text', `workExpInfo[${workExpCounter}].companyName`,'Company name');
            cardBody.appendChild(companyNameInput);

            const responsibilitiesTextarea = createTextarea(`workExpInfo[${workExpCounter}].responsibilities`,'responsibilities');
            cardBody.appendChild(responsibilitiesTextarea);

            const yearsOfWorkInput = createInputField('number', `workExpInfo[${workExpCounter}].years`,'years');
            cardBody.appendChild(yearsOfWorkInput);

            workExpCounter++; // Increment the work experience counter
        } else if (container === educationContainer) {
            const degreeInput = createInputField('text', `educationInfo[${educationCounter}].degree`,'Degree');
            cardBody.appendChild(degreeInput);

            const institutionInput = createInputField('text', `educationInfo[${educationCounter}].institution`,'Institution');
            cardBody.appendChild(institutionInput);

            const programTextarea = createTextarea(`educationInfo[${educationCounter}].program`,'Program');
            cardBody.appendChild(programTextarea);

            const startDateInput = createInputField('date', `educationInfo[${educationCounter}].startDate`,'Starting date');
            cardBody.appendChild(startDateInput);

            const endDateInput = createInputField('date', `educationInfo[${educationCounter}].endDate`,'Ending date');
            cardBody.appendChild(endDateInput);

            educationCounter++; // Increment the education counter
        } else if (container === contactsContainer) {
            const phone = createInputField('text', 'contacts.phone');
            cardBody.appendChild(phone);
        }
        let closeButton = document.createElement('button');
        closeButton.className = 'btn btn-danger'
        closeButton.classList.add('card-close');
        closeButton.textContent = 'удалить';
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
        inputElement.name = name; // Use the provided name directly
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

    const addContactsBtn = document.getElementById('add-contacts-btn');
    addContactsBtn.addEventListener('click', function () {
        createCard(contactsContainer);
    });

});