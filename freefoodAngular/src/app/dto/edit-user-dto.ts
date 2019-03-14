export class EditUserDto{
    id: String;
    name: String;
    email: String;
    

    constructor(name: String, email: String){
        this.name = name;
        this.email = email;
        
    }
}