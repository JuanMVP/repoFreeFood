export class AddUserDto{
    email: String;
    name: String;
    password: String;
    
    

    constructor(email: String, name: String, password: String){
        this.name = name;
        this.email = email;
        this.password = password;

        
    }
}