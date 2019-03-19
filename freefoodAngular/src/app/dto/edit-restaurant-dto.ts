export class EditRestaurantDto{
    id: String;
    name: String;
    address: String;
    intolerance: String;
    timetable: String;
    //picture: String;

    constructor(name: String, address: String, intolerance: String, timetable: String, /*picture: String*/){
        this.name = name;
        this.address = address;
        this.intolerance = intolerance;
        this.timetable = timetable;
        //this.picture=
    }

}