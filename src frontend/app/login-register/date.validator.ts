import { AbstractControl } from "@angular/forms";
import { ValidationErrors } from "@angular/forms";

export class DateValidatar{
    static dateValidate(date :AbstractControl):ValidationErrors|null{
        let today=new Date("2000-01-01");
        let controlValue = new Date(date.value);
        if(today<=controlValue)
        return {checkDate:true};
        else
        return null;
    }
}