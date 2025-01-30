document.getElementById('findRideBtn').addEventListener('click', function() {
    alert('Login for booking ride');
});


// Function to open the login/signup modal
function openLoginModal() {
    document.getElementById('login-modal').style.display = 'block';
}

// Function to close the login/signup modal
function closeLoginModal() {
    document.getElementById('login-modal').style.display = 'none';
}

// Function to open the career modal
function openCareerModal() {
    document.getElementById('career-modal').style.display = 'block';
}

// Function to close the career modal
function closeCareerModal() {
    document.getElementById('career-modal').style.display = 'none';
}

// Add event listeners to open and close buttons
document.getElementById('loginBtn').addEventListener('click', function(e) {
    e.preventDefault();
    openLoginModal();
});

document.getElementById('openCareerModal').addEventListener('click', function(e) {
    e.preventDefault();
    openCareerModal();
});

document.querySelector('.close-btn').addEventListener('click', closeLoginModal); // For login modal close button
document.querySelector('.close-btn').addEventListener('click', closeCareerModal); // For career modal close button
  
  
  findRide