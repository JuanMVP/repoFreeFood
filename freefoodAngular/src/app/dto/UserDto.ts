export class UserDto{
    email: String;
    password: String;
    name: String;
    picture: String;
    role: String;
    access_token: String = 'RL0Pj065laUucm3JOcKTJ5JjOoDCYma2';

    constructor(email: String, password: String, name: String, picture: String, role: String){
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }
}