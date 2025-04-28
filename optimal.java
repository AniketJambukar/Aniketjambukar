import java.util.*;

public class optimal {
    // Function to check if a page is in current frames
    public static boolean isPresent(int[] frames, int page) {
        for (int frame : frames) {
            if (frame == page) return true;
        }
        return false;
    }

    // Function to find the optimal page to replace
    public static int findOptimal(int[] pages, int[] frames, int index) {
        int pos = -1, farthest = index;

        for (int i = 0; i < frames.length; i++) {
            int j;
            for (j = index; j < pages.length; j++) {
                if (frames[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        pos = i;
                    }
                    break;
                }
            }
            if (j == pages.length) {
                return i; // This page will not be used again
            }
        }
        return (pos == -1) ? 0 : pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();
        int[] frames = new int[f];
        Arrays.fill(frames, -1); // Initialize frames with -1

        int pageFaults = 0, count = 0;

        for (int i = 0; i < n; i++) {
            if (!isPresent(frames, pages[i])) {
                if (count < f) {
                    frames[count++] = pages[i];
                } else {
                    int pos = findOptimal(pages, frames, i + 1);
                    frames[pos] = pages[i];
                }
                pageFaults++;

                // Print current state of frames
                System.out.print("Frames: ");
                for (int frame : frames) {
                    if (frame != -1)
                        System.out.print(frame + " ");
                    else
                        System.out.print("- ");
                }
                System.out.println();
            }
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
        sc.close();
    }
}

