import androidx.fragment.app.Fragment

interface NavigateHost {
    fun NavigateTo(fragment : Fragment , addToBackstack: Boolean)
}