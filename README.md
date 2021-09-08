# Recorder

**About this project:**<br><br>
1- Kotlin <br>
2- MVVM <br>
3- LiveData <br>
4- ViewModel <br>
5- Hilt DI <br>
6- Repository pattern <br>
7- Navigation component <br>
8- Data Binding <br>
9- Unit testing (local tests + instrumented tests) <br><br>

![architecture pattern](https://github.com/islamarr/recorder/blob/master/app/src/main/res/drawable/img_architecture_pattern.png)

**RecyclerView vs Listview**

- In RecyclerView, it is mandatory to use ViewHolder pattern Which optimize the performance.
- DiffUtil callback Which optimize the performance.  
- There is no official way to implement a horizontal ListView. But in RecyclerView by using Layout Manager you can do vertical, Staggered or Gridlayout

**Activities vs Fragments**

- I have used a single-activity architecture which allowed me to take full advantage of the Navigation component, which mean that a single activity that manages and host multiple fragments. 
- The fragment is more lite weight than Activity. 

**ViewModel vs AndroidViewModel**

AndroidViewModel is a Application context aware ViewModel. As I didn't need to use context inside my ViewModel classes, I've used ViewModel.

**Unit test vs instrumented test**

In cases where I wanted to test the behavior of some screens in a real device I used instrumented test by Espresso and Mockito.
In the functions I didn't need a physical device I used the local Unit Tests.

