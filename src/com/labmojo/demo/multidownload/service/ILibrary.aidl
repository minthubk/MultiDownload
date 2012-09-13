package com.labmojo.demo.multidownload.service;

import com.labmojo.demo.multidownload.service.ILibraryCallback;

interface ILibrary {

    
    void updateLibrary(boolean downloadBooks);

   
    void downloadBooks();
    
    
    void register(ILibraryCallback notifier);
}