export class ImageUploadDto{
    image: File;

    constructor(image: File){
        this.image = image;
    }
}