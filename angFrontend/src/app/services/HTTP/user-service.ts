import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { User } from '../../models/user';

export interface CurrentUserProfile {
    age: number;
    weight: number;
    height: number;
    activityLevel: string;
    firstName: string;
    lastName: string;
    tel: number;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {

    private http = inject(HttpClient);
    private apiUrl = 'http://localhost:8080/users';

    getUsers() {
        return this.http.get<User[]>(this.apiUrl);
    }

    getCurrentUserLogged() {
        return this.http.get<CurrentUserProfile>(`${this.apiUrl}/me`);
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

    updateAccountInfo(userData: {
        age: number | null;
        weight: number | null;
        height: number | null;
        activityLevel: string | null;
    }) {
        return this.http.put(this.apiUrl + '/update', userData);
    }

}
