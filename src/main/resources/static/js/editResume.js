document.addEventListener('DOMContentLoaded', function () {
    const workExpContainer = document.getElementById('work');
    const educationContainer = document.getElementById('education');
    const contactsContainer = document.getElementById('addContacts');
    console.log('Последний индекс опыта работы:', workExpLastIndex);
    console.log('Последний индекс образования:', eduLastIndex);
    let workCounter = workExpLastIndex
    let educationCounter = eduLastIndex


    function createField(container) {
        const field = document.createElement('div');
        field.className = 'field';

        if (container === workExpContainer) {
            workCounter++
            const workPositionInput = createInputField('text', `workExpInfoEdit[` + workCounter + `].position`, 'position');
            field.appendChild(workPositionInput);

            const companyNameInput = createInputField('text', `workExpInfoEdit[` + workCounter + `].companyName`, 'Company name');
            field.appendChild(companyNameInput);

            const responsibilitiesTextarea = createTextarea(`workExpInfoEdit[` + workCounter + `].responsibilities`, 'responsibilities');
            field.appendChild(responsibilitiesTextarea);

            const yearsOfWorkInput = createInputField('number', `workExpInfoEdit[` + workCounter + `].years`, 'years');
            field.appendChild(yearsOfWorkInput);

        } else if (container === educationContainer) {
            educationCounter++;
            const degreeInput = createInputField('text', `educationInfo[` + educationCounter + `].degree`, 'Degree');
            field.appendChild(degreeInput);

            const institutionInput = createInputField('text', `educationInfo[` + educationCounter + `].institution`, 'Institution');
            field.appendChild(institutionInput);

            const programTextarea = createTextarea(`educationInfo[` + educationCounter + `].program`, 'Program');
            field.appendChild(programTextarea);

            const startDateInput = createInputField('date', `educationInfo[` + educationCounter + `].startDate`, 'Starting date');
            field.appendChild(startDateInput);

            const endDateInput = createInputField('date', `educationInfo[` + educationCounter + `].endDate`, 'Ending date');
            field.appendChild(endDateInput);

        } else if (container === contactsContainer) {
            const phone = createInputField('text', 'contacts.phone');
            field.appendChild(phone);
        }
        let closeButton = document.createElement('button');
        closeButton.className = 'btn btn-danger mb-3 mt-2'
        closeButton.classList.add('card-close');
        closeButton.textContent = 'удалить';
        closeButton.addEventListener('click', () => {
            field.remove();
        });

        field.appendChild(closeButton);

        container.appendChild(field);
        container.appendChild(field);
    }

    function createInputField(type, name, label) {
        const container = document.createElement('div');

        const labelElement = document.createElement('label');
        container.appendChild(labelElement);

        const inputElement = document.createElement('input');
        inputElement.type = type;
        inputElement.name = name;
        inputElement.className = 'my-2';
        inputElement.value = label
        container.appendChild(inputElement);

        return container;
    }

    function createTextarea(name, label) {
        const container = document.createElement('div');
        container.className = 'mb-3';

        const labelElement = document.createElement('label');
        labelElement.textContent = label
        container.appendChild(labelElement);

        const textareaElement = document.createElement('textarea');
        textareaElement.className = 'form-control';
        textareaElement.name = name;
        container.appendChild(textareaElement);

        return container;
    }

    const addWorkExpBtn = document.getElementById('add-work-btn');
    addWorkExpBtn.addEventListener('click', function () {
        createField(workExpContainer);
    });

    const addEducationBtn = document.getElementById('add-education-btn');
    addEducationBtn.addEventListener('click', function () {
        createField(educationContainer);
    });

    const addContactsBtn = document.getElementById('add-contacts-btn');
    addContactsBtn.addEventListener('click', function () {
        createField(contactsContainer);
    });

});