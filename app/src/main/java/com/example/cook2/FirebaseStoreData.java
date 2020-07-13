package com.example.cook2;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseStoreData {
    static FirebaseFirestore mFirestore;

    public static FirebaseFirestore getmFirestore() {
        return mFirestore;
    }

    public static void setmFirestore(FirebaseFirestore mFirestore) {
        FirebaseStoreData.mFirestore = mFirestore;
    }
}
