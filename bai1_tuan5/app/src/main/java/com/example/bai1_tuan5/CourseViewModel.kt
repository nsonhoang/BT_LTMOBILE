import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai1_tuan5.Alo
import com.example.bai1_tuan5.Course
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> get() = _courses

    fun fetchCourses() {
        viewModelScope.launch {
            println("alo")
            db.collection("Course")

                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        for (document in querySnapshot.documents) {

                            val course: Course? = document.toObject(Course::class.java)


                            val documentId = document.id //
                            // Lấy ID của từng tài liệu trong kết quả truy vấn
                            Log.d(TAG, "Found document with ID: $documentId")
                            Log.d(TAG, "Document data: ${document.data}")
                            db.collection("Course").document(documentId)
                                .collection("List")
                                .get().addOnSuccessListener {
                                        querySnapshot->

                                    if(!querySnapshot.isEmpty){
                                        val listItem = mutableListOf<Alo?>()

                                        for(itemDocument in querySnapshot.documents){

                                            val itemdocumentId = itemDocument.id


                                            val itemCourse = itemDocument.toObject(Alo::class.java)
                                            listItem.add(itemCourse)

                                            if (course != null) {
                                                course.aloList = listItem.filterNotNull()
                                            } else {
                                                // Xử lý trường hợp course là null (nếu cần)
                                            }
                                        }
                                        _courses.value = if (course != null) listOf(course) else emptyList()

                                    }

                                }
                        }
                    } else {
                        Log.d(TAG, "No documents found matching the query.")
                    }
                }

                .addOnFailureListener { exception ->
                    // Xử lý lỗi nếu cần
                }
        }
    }
}