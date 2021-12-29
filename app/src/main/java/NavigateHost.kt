import androidx.fragment.app.Fragment

interface NavigateHost {
    fun NavigateTo(fragment : Fragment , addToBackstack: Boolean)
    fun saveToDB(ip : String?, port : String?, user : String?, pass :String?)
}