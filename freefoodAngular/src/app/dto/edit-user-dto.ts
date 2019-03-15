export class EditUserDto{
    id: String;
    name: String;
    email: String;
    password: String;
    

    constructor(name: String, email: String, password: String){
        this.name = name;
        this.email = email;
        this.password = password;
        
    }
}