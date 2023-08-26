package ui

enum class WindowClass {
    Compact, Medium, Expanded;

    companion object {
        @Suppress("MagicNumber")
        operator fun invoke() = when (windowWidth) {
            in 0..<600 -> Compact
            in 600..840 -> Medium
            else -> Expanded
        }
    }
}

