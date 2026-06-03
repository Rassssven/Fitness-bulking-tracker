import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {

    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/users';

    getUsers() {
        return this.http.get<User[]>(this.apiUrl);
    }

    updateUser(userData: {
        age: number;
        gender: string;
        height: number;
        activityLevel: string;
    }) {
        return this.http.put(`${this.apiUrl}/me`, userData);
    }

    updateRole(id: number, Role: string) {
        return this.http.put(`${this.apiUrl}/${id}/role?role=${Role}`, {});
    }

    searchUser(name: string) {
        return this.http.get<User[]>(`${this.apiUrl}/search?name=${name}`);
    }

}
