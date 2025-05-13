function validateForm() {
    let isValid = true;
    const name = document.getElementById('name');
    const email = document.getElementById('email');
    const department = document.getElementById('department');
    const age = document.getElementById('age');


    [name, email, department, age].forEach(input => {
        input.classList.remove('border-red-500');
        input.nextElementSibling.textContent = '';
    });

    if (!name.value || name.value.length > 100) {
        name.classList.add('border-red-500');
        name.nextElementSibling.textContent = 'Name is mandatory and must be less than 100 characters';
        isValid = false;
    }

    if (!email.value || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value)) {
        email.classList.add('border-red-500');
        email.nextElementSibling.textContent = 'Valid email is mandatory';
        isValid = false;
    }

    if (!department.value || department.value.length > 50) {
        department.classList.add('border-red-500');
        department.nextElementSibling.textContent = 'Department is mandatory and must be less than 50 characters';
        isValid = false;
    }

    if (!age.value || age.value < 18 || age.value > 100) {
        age.classList.add('border-red-500');
        age.nextElementSibling.textContent = 'Age must be between 18 and 100';
        isValid = false;
    }

    return isValid;
}