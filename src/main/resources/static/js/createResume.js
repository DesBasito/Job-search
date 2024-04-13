document.addEventListener('DOMContentLoaded', function () {
    const workExpContainer = document.getElementById('workExp');
    const educationContainer = document.getElementById('educationInfo');
    const contactsContainer = document.getElementById('contacts');

    function createCard(container) {
        const cardBody = document.createElement('div');
        cardBody.className = 'card-body';


        if (container === workExpContainer) {
            const workPositionInput = createInputField('text', 'Work Position');
            cardBody.appendChild(workPositionInput);

            const companyNameInput = createInputField('text', 'Company Name');
            cardBody.appendChild(companyNameInput);

            const responsibilitiesTextarea = createTextarea('Responsibilities');
            cardBody.appendChild(responsibilitiesTextarea);

            const yearsOfWorkInput = createInputField('number', 'Years of Work');
            cardBody.appendChild(yearsOfWorkInput);
        } else if (container === educationContainer) {
            const degreeInput = createInputField('text', 'Degree');
            cardBody.appendChild(degreeInput);

            const institutionInput = createInputField('text', 'Institution');
            cardBody.appendChild(institutionInput);

            const programTextarea = createTextarea('Program');
            cardBody.appendChild(programTextarea);

            const startDateInput = createInputField('date', 'Start Date');
            cardBody.appendChild(startDateInput);

            const endDateInput = createInputField('date', 'End Date');
            cardBody.appendChild(endDateInput);
        } else if (container === contactsContainer) {
            const otherSocialMedia = createInputField('text', 'Other Social Media');
            cardBody.appendChild(otherSocialMedia);

            otherSocialMedia.addEventListener('input', function () {
                const socialMediaName = otherSocialMedia.value.trim();

                if (socialMediaName !== '') {
                    const socialMediaInput = createInputField('text', socialMediaName);
                    cardBody.appendChild(socialMediaInput);

                    otherSocialMedia.value = '';
                }
            });
        }


        container.appendChild(cardBody);
    }

    function createInputField(type, label) {
        // Create container for label and input
        const container = document.createElement('div');
        container.className = 'mb-3';

        // Create label element
        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        // Create input element
        const inputElement = document.createElement('input');
        inputElement.type = type;
        inputElement.className = 'form-control';
        container.appendChild(inputElement);

        return container;
    }

    function createTextarea(label) {
        // Create container for label and textarea
        const container = document.createElement('div');
        container.className = 'mb-3';

        // Create label element
        const labelElement = document.createElement('label');
        labelElement.textContent = label;
        labelElement.className = 'form-label';
        container.appendChild(labelElement);

        // Create textarea element
        const textareaElement = document.createElement('textarea');
        textareaElement.className = 'form-control';
        container.appendChild(textareaElement);

        return container;
    }

    // Event listener for adding Work Experience card
    const addWorkExpBtn = document.getElementById('add-work-btn');
    addWorkExpBtn.addEventListener('click', function () {
        createCard(workExpContainer);
    });

    // Event listener for adding Education card
    const addEducationBtn = document.getElementById('add-education-btn');
    addEducationBtn.addEventListener('click', function () {
        createCard(educationContainer);
    });

    // Event listener for adding Contacts Info card
    const addContactsBtn = document.getElementById('add-contacts-btn');
    addContactsBtn.addEventListener('click', function () {
        createCard(contactsContainer);
    });

    $(document).ready(function() {
        $('form').submit(function(event) {
            event.preventDefault();
            const formData = $(this).serializeArray();
            const jsonData = {};
            $.each(formData, function() {
                jsonData[this.name] = this.value;
            });

            $.ajax({
                type: 'POST',
                url: '/resume/create',
                contentType: 'application/json',
                data: JSON.stringify(jsonData),
                success: function(response) {
                    // Handle success response
                    console.log(response);
                },
                error: function(error) {
                    // Handle error response
                    console.error(error);
                }
            });
        });
    });

// $(document).ready(function() {
//     $('#add-work-btn').click(function() {
//         const workSection = $('#workExp').clone();
//         $('#work').append(workSection);
//     });
// });
//
// $(document).ready(function() {
//     $('#add-education-btn').click(function() {
//         const educationSection = $('#education').clone();
//         $('#educationInfo').append(educationSection);
//     });
// });
//
// $(document).ready(function() {
//     $('#add-contacts-btn').click(function() {
//         const contactsSection = $('#contacts').clone();
//         $('#contacts').append(contactsSection);
//     });
// });
});
