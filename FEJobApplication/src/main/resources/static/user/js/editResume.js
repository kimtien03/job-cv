document.addEventListener('DOMContentLoaded', function() {
    // Theme switching
    document.querySelectorAll('[data-theme]').forEach(button => {
        button.addEventListener('click', (e) => {
            const theme = e.target.dataset.theme;
            document.body.className = `theme-${theme}`;
            localStorage.setItem('cv-theme', theme);
        });
    });

    document.addEventListener('DOMContentLoaded', function() {
        // Theme switching
        document.addEventListener('DOMContentLoaded', function() {
            // Theme switching
            document.querySelectorAll('[data-theme]').forEach(button => {
                button.addEventListener('click', (e) => {
                    const theme = e.target.dataset.theme;

                    // Change only the cv-preview class with the appropriate theme
                    const cvPreview = document.querySelector('.cv-preview');
                    cvPreview.className = `cv-preview template-${cvPreview.className.split(' ').find(cl => cl.startsWith('template-')) || ''} theme-${theme}`;

                    localStorage.setItem('cv-theme', theme);
                });
            });

            // Template switching
            document.querySelectorAll('[data-template]').forEach(button => {
                button.addEventListener('click', (e) => {
                    const template = e.target.dataset.template;
                    document.querySelector('.cv-preview').className = `cv-preview template-${template}`;
                    localStorage.setItem('cv-template', template);
                });
            });
        });

        // Template switching
        document.querySelectorAll('[data-template]').forEach(button => {
            button.addEventListener('click', (e) => {
                const template = e.target.dataset.template;
                document.querySelector('.cv-preview').className = `cv-preview template-${template}`;
                localStorage.setItem('cv-template', template);
            });
        });
    });

    // Template switching
    document.querySelectorAll('[data-template]').forEach(button => {
        button.addEventListener('click', (e) => {
            const template = e.target.dataset.template;
            document.querySelector('.cv-preview').className = `cv-preview template-${template}`;
            localStorage.setItem('cv-template', template);
        });
    });

    // Save functionality
    document.getElementById('saveBtn').addEventListener('click', () => {
        const cvData = {
            personalInfo: {
                firstName: document.querySelector('input[placeholder="First Name"]')?.value,
                lastName: document.querySelector('input[placeholder="Last Name"]')?.value,
                title: document.querySelector('input[placeholder="Professional Title"]')?.value,
                email: document.querySelector('input[placeholder="Email"]')?.value,
                phone: document.querySelector('input[placeholder="Phone Number"]')?.value,
                location: document.querySelector('input[placeholder="Location"]')?.value,
                summary: document.querySelector('textarea[placeholder="Brief professional summary"]')?.value
            },
            theme: localStorage.getItem('cv-theme'),
            template: localStorage.getItem('cv-template')
        };

        localStorage.setItem('cv-data', JSON.stringify(cvData));
        alert('CV saved successfully!');
    });

    // Load saved data
    const savedData = JSON.parse(localStorage.getItem('cv-data') || '{}');
    if (savedData.personalInfo) {
        Object.entries(savedData.personalInfo).forEach(([key, value]) => {
            const input = document.querySelector(`input[placeholder="${key}"]`);
            if (input) input.value = value;
        });
    }
    if (savedData.theme) {
        document.body.className = `theme-${savedData.theme}`;
    }
    if (savedData.template) {
        document.querySelector('.cv-preview').className = `cv-preview template-${savedData.template}`;
    }

    // Personal Information updates
    const updateField = (inputSelector, previewSelector) => {
        const input = document.querySelector(inputSelector);
        const preview = document.querySelector(previewSelector);
        if (input && preview) {
            ['input', 'keyup'].forEach(eventType => {
                input.addEventListener(eventType, (e) => {
                    preview.textContent = e.target.value;
                });
            });
        }
    };

    // Helper function to update name
    const updateName = () => {
        const firstName = document.querySelector('input[placeholder="First Name"]')?.value || '';
        const lastName = document.querySelector('input[placeholder="Last Name"]')?.value || '';
        const previewName = document.querySelector('.preview-name');
        if (previewName) {
            previewName.textContent = `${firstName} ${lastName}`;
        }
    };

    // Add listeners to name fields
    const firstNameInput = document.querySelector('input[placeholder="First Name"]');
    const lastNameInput = document.querySelector('input[placeholder="Last Name"]');
    if (firstNameInput) firstNameInput.addEventListener('input', updateName);
    if (lastNameInput) lastNameInput.addEventListener('input', updateName);

    // Update contact information
    const updateContact = (inputSelector, iconClass, index) => {
        const input = document.querySelector(inputSelector);
        const preview = document.querySelector(`.preview-contact span:nth-child(${index})`);
        if (input && preview) {
            ['input', 'keyup'].forEach(eventType => {
                input.addEventListener(eventType, (e) => {
                    preview.innerHTML = `<i class="${iconClass}"></i> ${e.target.value}`;
                });
            });
        }
    };

    // Professional title
    const titleInput = document.querySelector('input[placeholder="Professional Title"]');
    const previewTitle = document.querySelector('.preview-title');
    if (titleInput && previewTitle) {
        titleInput.addEventListener('input', (e) => {
            previewTitle.textContent = e.target.value;
        });
    }

    // Contact details
    updateContact('input[placeholder="Email"]', 'bi bi-envelope', 1);
    updateContact('input[placeholder="Phone Number"]', 'bi bi-telephone', 2);
    updateContact('input[placeholder="Location"]', 'bi bi-geo-alt', 3);

    // Summary
    updateField('textarea[placeholder="Brief professional summary"]', '.preview-summary');

    // Experience
    updateField('input[placeholder="Company Name"]', '.preview-experience h4');
    updateField('input[placeholder="Job Title"]', '.preview-experience .position');

    // Handle job responsibilities
    const responsibilitiesInput = document.querySelector('textarea[placeholder="Job Responsibilities"]');
    if (responsibilitiesInput) {
        responsibilitiesInput.addEventListener('input', (e) => {
            const responsibilities = e.target.value.split('\n').filter(item => item.trim() !== '');
            const ul = document.querySelector('.preview-experience ul');
            if (ul) {
                ul.innerHTML = responsibilities.map(resp => `<li>${resp}</li>`).join('');
            }
        });
    }

    // Education
    updateField('input[placeholder="Institution Name"]', '.preview-education h4');
    updateField('input[placeholder="Degree/Qualification"]', '.preview-education p:first-of-type');


    // Add Experience, Education, and Skills functionality
    // Add Experience button functionality
    document.getElementById('addExperience').addEventListener('click', () => {
        const experienceContainer = document.getElementById('experienceContainer');
        const newExperience = document.createElement('div');
        newExperience.className = 'experience-entry card mb-3';
        newExperience.innerHTML = `
      <div class="card-body">
        <div class="d-flex justify-content-end gap-2 mb-2">
          <button type="button" class="btn btn-secondary btn-sm move-up">
            <i class="bi bi-arrow-up"></i>
          </button>
          <button type="button" class="btn btn-secondary btn-sm move-down">
            <i class="bi bi-arrow-down"></i>
          </button>
          <button type="button" class="btn btn-danger btn-sm remove-entry">
            <i class="bi bi-trash"></i>
          </button>
        </div>
        <input type="text" class="form-control mb-2" placeholder="Company Name">
        <input type="text" class="form-control mb-2" placeholder="Job Title">
        <div class="row mb-2">
          <div class="col">
            <input type="date" class="form-control" placeholder="Start Date">
          </div>
          <div class="col">
            <input type="date" class="form-control" placeholder="End Date">
          </div>
        </div>
        <textarea class="form-control" rows="3" placeholder="Job Responsibilities"></textarea>
      </div>
    `;
        experienceContainer.appendChild(newExperience);

        // Add remove button functionality
        const removeBtn = newExperience.querySelector('.remove-entry');
        if (removeBtn) {
            removeBtn.addEventListener('click', () => {
                newExperience.remove();
                updateExperiencePreview();
            });
        }

        // Add move buttons functionality
        const moveUpBtn = newExperience.querySelector('.move-up');
        const moveDownBtn = newExperience.querySelector('.move-down');

        if (moveUpBtn) {
            moveUpBtn.addEventListener('click', () => {
                const prev = newExperience.previousElementSibling;
                if (prev && !prev.id) {
                    experienceContainer.insertBefore(newExperience, prev);
                    updateExperiencePreview();
                }
            });
        }

        if (moveDownBtn) {
            moveDownBtn.addEventListener('click', () => {
                const next = newExperience.nextElementSibling;
                if (next && !next.id) {
                    experienceContainer.insertBefore(next, newExperience);
                    updateExperiencePreview();
                }
            });
        }

        // Add event listeners to update preview
        const inputs = newExperience.querySelectorAll('input, textarea');
        inputs.forEach(input => {
            input.addEventListener('input', () => {
                updateExperiencePreview();
            });
        });
    });

    // Add Education button functionality
    document.getElementById('addEducation').addEventListener('click', () => {
        const educationContainer = document.querySelector('.form-step[data-step="4"]');
        const newEducation = document.createElement('div');
        newEducation.className = 'mb-3';
        newEducation.innerHTML = `
      <div class="d-flex justify-content-end gap-2 mb-2">
        <button type="button" class="btn btn-secondary btn-sm move-up">
          <i class="bi bi-arrow-up"></i>
        </button>
        <button type="button" class="btn btn-secondary btn-sm move-down">
          <i class="bi bi-arrow-down"></i>
        </button>
        <button type="button" class="btn btn-danger btn-sm remove-entry">
          <i class="bi bi-trash"></i>
        </button>
      </div>
      <input type="text" class="form-control mb-2" placeholder="Institution Name">
      <input type="text" class="form-control mb-2" placeholder="Degree/Qualification">
      <input type="number" class="form-control mb-2" placeholder="Graduation Year">
      <textarea class="form-control" rows="2" placeholder="Academic Achievements"></textarea>
    `;
        educationContainer.insertBefore(newEducation, document.getElementById('addEducation'));

        // Add remove button functionality
        const removeBtn = newEducation.querySelector('.remove-entry');
        if (removeBtn) {
            removeBtn.addEventListener('click', () => {
                newEducation.remove();
                updateEducationPreview();
            });
        }

        // Add move buttons functionality
        const moveUpBtn = newEducation.querySelector('.move-up');
        const moveDownBtn = newEducation.querySelector('.move-down');

        if (moveUpBtn) {
            moveUpBtn.addEventListener('click', () => {
                const prev = newEducation.previousElementSibling;
                if (prev && !prev.id) {
                    educationContainer.insertBefore(newEducation, prev);
                    updateEducationPreview();
                }
            });
        }

        if (moveDownBtn) {
            moveDownBtn.addEventListener('click', () => {
                const next = newEducation.nextElementSibling;
                if (next && !next.id) {
                    educationContainer.insertBefore(next, newEducation);
                    updateEducationPreview();
                }
            });
        }

        // Add event listeners to update preview
        const inputs = newEducation.querySelectorAll('input, textarea');
        inputs.forEach(input => {
            input.addEventListener('input', () => {
                updateEducationPreview();
            });
        });
    });

    // Add Skill button functionality
    document.getElementById('addSkill').addEventListener('click', () => {
        const skillsContainer = document.querySelector('.form-step[data-step="5"]');
        const addSkillBtn = document.getElementById('addSkill');
        const newSkill = document.createElement('div');
        newSkill.className = 'skill-entry mb-2';
        newSkill.innerHTML = `
      <div class="d-flex gap-2">
        <button type="button" class="btn btn-secondary btn-sm move-up align-self-start">
          <i class="bi bi-arrow-up"></i>
        </button>
        <button type="button" class="btn btn-secondary btn-sm move-down align-self-start">
          <i class="bi bi-arrow-down"></i>
        </button>
        <div class="flex-grow-1">
          <input type="text" class="form-control mb-2" placeholder="Skill Name">
          <select class="form-select">
            <option>Beginner</option>
            <option>Intermediate</option>
            <option>Advanced</option>
            <option>Expert</option>
          </select>
        </div>
        <button type="button" class="btn btn-danger btn-sm remove-entry align-self-start">
          <i class="bi bi-trash"></i>
        </button>
      </div>
    `;
        skillsContainer.insertBefore(newSkill, addSkillBtn);

        // Add event listeners to update preview
        const inputs = newSkill.querySelectorAll('input, select');
        inputs.forEach(input => {
            input.addEventListener('input', () => {
                updateSkillsPreview();
            });
        });

        // Add remove button functionality
        const removeBtn = newSkill.querySelector('.remove-entry');
        if (removeBtn) {
            removeBtn.addEventListener('click', () => {
                newSkill.remove();
                updateSkillsPreview();
            });
        }

        // Add move buttons functionality
        const moveUpBtn = newSkill.querySelector('.move-up');
        const moveDownBtn = newSkill.querySelector('.move-down');

        if (moveUpBtn) {
            moveUpBtn.addEventListener('click', () => {
                const prev = newSkill.previousElementSibling;
                if (prev && !prev.id) {
                    skillsContainer.insertBefore(newSkill, prev);
                    updateSkillsPreview();
                }
            });
        }

        if (moveDownBtn) {
            moveDownBtn.addEventListener('click', () => {
                const next = newSkill.nextElementSibling;
                if (next && !next.id) {
                    skillsContainer.insertBefore(next, newSkill);
                    updateSkillsPreview();
                }
            });
        }
    });

    // Preview update functions
    function updateExperiencePreview() {
        const previewExperience = document.querySelector('.preview-experience');
        previewExperience.innerHTML = '';

        document.querySelectorAll('.experience-entry').forEach(entry => {
            const company = entry.querySelector('input[placeholder="Company Name"]').value;
            const title = entry.querySelector('input[placeholder="Job Title"]').value;
            const startDate = entry.querySelector('input[type="date"]:first-of-type').value;
            const endDate = entry.querySelector('input[type="date"]:last-of-type').value;
            const responsibilities = entry.querySelector('textarea').value;

            if (company || title) {
                const expElement = document.createElement('div');
                const dateRange = startDate || endDate ?
                    `<p class="dates">${startDate} - ${endDate || 'Present'}</p>` : '';
                expElement.innerHTML = `
          <h4>${company}</h4>
          <p class="position">${title}</p>
          ${dateRange}
          <ul>${responsibilities.split('\n').map(r => r.trim() ? `<li>${r}</li>` : '').join('')}</ul>
        `;
                previewExperience.appendChild(expElement);
            }
        });
    }

    function updateEducationPreview() {
        const previewEducation = document.querySelector('.preview-education');
        previewEducation.innerHTML = '';

        document.querySelectorAll('.form-step[data-step="4"] .mb-3').forEach(entry => {
            const institution = entry.querySelector('input[placeholder="Institution Name"]')?.value;
            const degree = entry.querySelector('input[placeholder="Degree/Qualification"]')?.value;
            const year = entry.querySelector('input[placeholder="Graduation Year"]')?.value;

            if (institution || degree) {
                const eduElement = document.createElement('div');
                eduElement.innerHTML = `
          <h4>${institution}</h4>
          <p>${degree}</p>
          ${year ? `<p>${year}</p>` : ''}
        `;
                previewEducation.appendChild(eduElement);
            }
        });
    }

    function updateSkillsPreview() {
        const previewSkills = document.querySelector('.preview-skills');
        previewSkills.innerHTML = '';

        document.querySelectorAll('.skill-entry input[placeholder="Skill Name"]').forEach(skill => {
            if (skill.value.trim()) {
                const skillBadge = document.createElement('span');
                skillBadge.className = 'skill-badge';
                skillBadge.textContent = skill.value;
                previewSkills.appendChild(skillBadge);
            }
        });
    }

    // Navigation
    const navigation = () => {
        let currentStep = 1;
        const totalSteps = 5;
        const prevBtn = document.querySelector('.prev-step');
        const nextBtn = document.querySelector('.next-step');
        const submitBtn = document.querySelector('.submit-cv');

        // Add click handlers to process steps
        document.querySelectorAll('.step:not(#addStepButton)').forEach(step => {
            // Add move buttons
            const moveButtons = document.createElement('div');
            moveButtons.className = 'move-buttons';
            moveButtons.innerHTML = `
        <button class="move-button move-left" title="Move Left">
          <i class="bi bi-chevron-left"></i>
        </button>
        <button class="move-button move-right" title="Move Right">
          <i class="bi bi-chevron-right"></i>
        </button>
      `;
            step.appendChild(moveButtons);

            // Click handler for step
            step.addEventListener('click', () => {
                const stepNum = parseInt(step.dataset.step);
                if (stepNum) {
                    currentStep = stepNum;
                    updateSteps();
                }
            });

            // Move buttons handlers
            const moveLeft = moveButtons.querySelector('.move-left');
            const moveRight = moveButtons.querySelector('.move-right');

            moveLeft.addEventListener('click', (e) => {
                e.stopPropagation();
                const prev = step.previousElementSibling;
                if (prev && !prev.id) {
                    const stepNumber = parseInt(step.dataset.step);
                    const prevNumber = parseInt(prev.dataset.step);

                    // Move the step in the process steps
                    step.parentNode.insertBefore(step, prev);

                    // Move the corresponding form step
                    const formStep = document.querySelector(`.form-step[data-step="${stepNumber}"]`);
                    const prevFormStep = document.querySelector(`.form-step[data-step="${prevNumber}"]`);
                    if (formStep && prevFormStep && formStep.parentNode) {
                        formStep.parentNode.insertBefore(formStep, prevFormStep);
                    }

                    updateStepNumbers();
                    updateAllPreviews();

                    // Update the preview sections order
                    const previewSections = document.querySelectorAll('.preview-section');
                    if (previewSections[stepNumber - 1] && previewSections[prevNumber - 1]) {
                        const previewParent = previewSections[0].parentNode;
                        previewParent.insertBefore(previewSections[stepNumber - 1], previewSections[prevNumber - 1]);
                    }
                }
            });

            moveRight.addEventListener('click', (e) => {
                e.stopPropagation();
                const next = step.nextElementSibling;
                if (next && !next.id) {
                    const stepNumber = parseInt(step.dataset.step);
                    const nextNumber = parseInt(next.dataset.step);

                    // Move the step in the process steps
                    step.parentNode.insertBefore(next, step);

                    // Move the corresponding form step
                    const formStep = document.querySelector(`.form-step[data-step="${stepNumber}"]`);
                    const nextFormStep = document.querySelector(`.form-step[data-step="${nextNumber}"]`);
                    if (formStep && nextFormStep && formStep.parentNode) {
                        formStep.parentNode.insertBefore(nextFormStep, formStep);
                    }

                    updateStepNumbers();
                    updateAllPreviews();

                    // Update the preview sections order
                    const previewSections = document.querySelectorAll('.preview-section');
                    if (previewSections[stepNumber - 1] && previewSections[nextNumber - 1]) {
                        const previewParent = previewSections[0].parentNode;
                        previewParent.insertBefore(previewSections[nextNumber - 1], previewSections[stepNumber - 1]);
                    }
                }
            });
        });

        function updateAllPreviews() {
            updateExperiencePreview();
            updateEducationPreview();
            updateSkillsPreview();
        }

        function updateStepNumbers() {
            const steps = document.querySelectorAll('.step:not(#addStepButton)');
            const formSteps = document.querySelectorAll('.form-step');
            const previewSections = document.querySelectorAll('.preview-section');

            steps.forEach((step, index) => {
                const number = index + 1;
                // Update process step
                step.setAttribute('data-step', number);
                step.querySelector('.step-circle').textContent = number;

                // Update form step
                if (formSteps[index]) {
                    formSteps[index].setAttribute('data-step', number);
                }

                // Update preview section
                if (previewSections[index]) {
                    previewSections[index].setAttribute('data-step', number);
                }
            });
        }

        function updateSteps() {
            // Hide all steps first
            document.querySelectorAll('.form-step').forEach(step => {
                step.classList.remove('active');
            });

            // Show current step
            const currentStepElement = document.querySelector(`.form-step[data-step="${currentStep}"]`);
            if (currentStepElement) {
                currentStepElement.classList.add('active');
            }

            // Update step indicators
            document.querySelectorAll('.step').forEach(step => {
                const stepNum = parseInt(step.dataset.step);
                step.classList.remove('active', 'completed');
                if (stepNum < currentStep) {
                    step.classList.add('completed');
                } else if (stepNum === currentStep) {
                    step.classList.add('active');
                }
            });

            // Update buttons
            if (prevBtn) prevBtn.disabled = currentStep === 1;

            if (nextBtn && submitBtn) {
                if (currentStep === totalSteps) {
                    nextBtn.style.display = 'none';
                    submitBtn.style.display = 'block';
                } else {
                    nextBtn.style.display = 'block';
                    submitBtn.style.display = 'none';
                }
            }
        }

        // Add click handlers
        if (prevBtn) {
            prevBtn.addEventListener('click', () => {
                if (currentStep > 1) {
                    currentStep--;
                    updateSteps();
                }
            });
        }

        if (nextBtn) {
            nextBtn.addEventListener('click', () => {
                if (currentStep < totalSteps) {
                    currentStep++;
                    updateSteps();
                }
            });
        }

        // Initialize first step
        updateSteps();
    };

    // Add custom section functionality
    document.getElementById('addStepButton').addEventListener('click', () => {
        const sectionName = prompt('Enter section name:');
        if (!sectionName) return;

        // Create new step
        const steps = document.querySelectorAll('.step:not(#addStepButton)');
        const newStepNumber = steps.length + 1;

        const newStep = document.createElement('div');
        newStep.className = 'step';
        newStep.setAttribute('data-step', newStepNumber);
        newStep.draggable = true;
        newStep.innerHTML = `
      <div class="step-circle">${newStepNumber}</div>
      <div class="step-label">${sectionName}</div>
    `;

        // Add movement buttons
        const moveButtonsContainer = document.createElement('div');
        moveButtonsContainer.className = 'move-buttons';
        moveButtonsContainer.innerHTML = `
      <button class="btn btn-sm btn-secondary move-left" title="Move Left">
        <i class="bi bi-arrow-left"></i>
      </button>
      <button class="btn btn-sm btn-secondary move-right" title="Move Right">
        <i class="bi bi-arrow-right"></i>
      </button>
    `;
        newStep.appendChild(moveButtonsContainer);

        // Add movement functionality
        const moveLeft = moveButtonsContainer.querySelector('.move-left');
        const moveRight = moveButtonsContainer.querySelector('.move-right');

        moveLeft.addEventListener('click', (e) => {
            e.stopPropagation();
            const prev = newStep.previousElementSibling;
            if (prev && !prev.id) {
                prev.before(newStep);
                updateStepNumbers();
                updatePreview();
            }
        });

        moveRight.addEventListener('click', (e) => {
            e.stopPropagation();
            const next = newStep.nextElementSibling;
            if (next && !next.id) {
                next.after(newStep);
                updateStepNumbers();
                updatePreview();
            }
        });

        // Insert before add button
        const addButton = document.getElementById('addStepButton');
        addButton.parentNode.insertBefore(newStep, addButton);

        // Create corresponding form step
        const formStep = document.createElement('div');
        formStep.className = 'form-step';
        formStep.setAttribute('data-step', newStepNumber);
        formStep.innerHTML = `
      <h3 class="mb-4">${sectionName}</h3>
      <div class="custom-fields-container">
        <div class="field-entry mb-3">
          <input type="text" class="form-control mb-2" placeholder="Field Name">
          <input type="text" class="form-control" placeholder="Field Value">
        </div>
      </div>
      <button type="button" class="btn btn-outline-primary add-field">
        <i class="bi bi-plus-circle"></i> Add Field
      </button>
    `;

        // Add field button functionality
        formStep.querySelector('.add-field').addEventListener('click', () => {
            const container = formStep.querySelector('.custom-fields-container');
            const newField = document.createElement('div');
            newField.className = 'field-entry mb-3';
            newField.innerHTML = `
        <div class="d-flex gap-2 mb-2">
          <input type="text" class="form-control" placeholder="Field Name">
          <input type="text" class="form-control" placeholder="Field Value">
          <button type="button" class="btn btn-danger btn-sm remove-field">
            <i class="bi bi-trash"></i>
          </button>
        </div>
      `;

            newField.querySelector('.remove-field').addEventListener('click', () => {
                newField.remove();
                updatePreview();
            });

            container.appendChild(newField);
        });

        document.getElementById('cvForm').appendChild(formStep);
    });

    function updateStepNumbers() {
        const steps = document.querySelectorAll('.step:not(#addStepButton)');
        steps.forEach((step, index) => {
            const number = index + 1;
            step.setAttribute('data-step', number);
            step.querySelector('.step-circle').textContent = number;

            // Update corresponding form step
            const formStep = document.querySelector(`.form-step[data-step="${number}"]`);
            if (formStep) {
                formStep.setAttribute('data-step', number);
            }
        });
    }

    // Initialize navigation
    navigation();
});