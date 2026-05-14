var db = [
        {"type":"student","name":"Ali","surname":"Ali","email":"ali@mail","password":"123"},
        {"type":"secretary","name":"Veli","surname":"Veli","email":"veli@mail","password":"123"},
        {"type":"instructor","name":"Hasan","surname":"Hasan","email":"hasan@mail","password":"123"}];

function getPassword(mail){
    for(i=0;i<db.length;i++){
        let userEmail = db[i]["email"];
        if(userEmail == mail){
            console.log(userEmail);
            return [db[i]["password"], db[i]["type"]];
        }
    }
    return false;
}
function addUser(userData){
    db.push(userData);
}

window.onload = function () {
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');
    
    const signInReal = document.getElementById('realSignIn');
    const signInForm = document.getElementById('signInForm');

    const singUpReal = document.getElementById('signUpReal');
    const signUpForm = document.getElementById('signUpForm');

    
    singUpReal.addEventListener('click',()=>{
        let type = document.getElementById("customSelect").value;
        let firstName = document.getElementById('firstName').value;
        if (firstName==""){
            console.log("Firstname cannot be empty")
            return false;
        }
        let lastName = document.getElementById('lastName').value;
        if (lastName==""){
            console.log("Lastname cannot be empty")
            return false;
        }
        let userName = document.getElementById('userName').value;
        if (userName==""){
            console.log("Username cannot be empty")
            return false;
        }
        let password = document.getElementById('password').value;
        if (password==""){
            console.log("Password cannot be empty")
            return false;
        }
        let signUpDic = {"type":type,"name":firstName,"surname":lastName,"email":userName,"password":password};
        addUser(signUpDic)
        signUpForm.action = type;
    });

    signInReal.addEventListener('click', () => {
        console.log("clicked");
        email = document.getElementById("emailSignIn").value;
        if(email==""){
            console.log("Email cannot be empty");
            return false;
        }
        password = document.getElementById("passwordSignIn").value;
        if(password==""){
            console.log("Password cannot be empty");
            return false;
        }
        let passAndtype = getPassword(email);
        pass = passAndtype[0];
        redirect = passAndtype[1];
        if(pass != undefined){
            if(pass == password){
                console.log("Correct");
                signInForm.action = redirect;
            }
            else{console.log("Wrong mail or password");}
        }else{console.log("Wrong mail or password");}
        return false;
    });

    signUpButton.addEventListener('click', () => {
        container.classList.add("right-panel-active");
    });

    signInButton.addEventListener('click', () => {
        container.classList.remove("right-panel-active");

    });
}


