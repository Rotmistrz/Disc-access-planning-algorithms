public class DiscAccessPlanningAlgorithms {
    public static void main(String[] args) {
        Head fcfsHead = new Head(0);
        FCFS fcfs = new FCFS(fcfsHead);
        SSTF sstf = new SSTF(new Head(0));

        try {
            fcfs.addRequest(new Request(1, 12));
            fcfs.addRequest(new Request(5, 45));
            fcfs.addRequest(new Request(6, 777));
            fcfs.addRequest(new Request(13, 192));
            fcfs.addRequest(new Request(1, 932));
            fcfs.addRequest(new Request(1, 442));
            fcfs.addRequest(new Request(1, 174));
            fcfs.addRequest(new Request(84, 123));
            fcfs.addRequest(new Request(71, 999));
            fcfs.addRequest(new Request(16, 44));

            sstf.addRequest(new Request(1, 442));
            sstf.addRequest(new Request(1, 12));
            sstf.addRequest(new Request(5, 45));
            sstf.addRequest(new Request(6, 777));
            sstf.addRequest(new Request(13, 192));
            sstf.addRequest(new Request(1, 932));
            sstf.addRequest(new Request(1, 174));
            sstf.addRequest(new Request(84, 123));
            sstf.addRequest(new Request(71, 999));
            sstf.addRequest(new Request(16, 44));
        } catch (OutOfDiscException e) {
            e.printStackTrace();
        }

        System.out.println("FCFS: " + fcfs.countAveragePath());
        System.out.println("SSTF: " + sstf.countAveragePath());



    }
}
