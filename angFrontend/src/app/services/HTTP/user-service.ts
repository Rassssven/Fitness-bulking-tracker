import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {

    private http = inject(HttpClient);
    private apiUrl = 'https://localhost:8080/users';

    updateUser(userData: {
        age: number;
        gender: string;
        height: number;
        activityLevel: string;
    }) {
        return this.http.put(`${this.apiUrl}/me`, userData);
    }

}
