export class EditRestaurantDto{
    id: string;
    name: string;
    address: string;
    intolerance: string;
    timetable: string;
    //picture: String;

    constructor(name: string, address: string, intolerance: string, timetable: string, /*picture: String*/){
        this.name = name;
        this.address = address;
        this.intolerance = intolerance;
        this.timetable = timetable;
        //this.picture=
    }

}