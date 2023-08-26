package ui

private const val COMPACT_TO_MEDIUM = 600
private const val MEDIUM_TO_EXPANDED = 840

enum class WindowClass {
    Compact, Medium, Expanded;

    companion object {
        operator fun invoke() = when (windowWidth) {
            in 0..<COMPACT_TO_MEDIUM -> Compact
            in COMPACT_TO_MEDIUM..MEDIUM_TO_EXPANDED -> Medium
            else -> Expanded
        }
    }
}

