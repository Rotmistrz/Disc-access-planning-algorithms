public class DiscAccessPlanningAlgorithms {
    public static void main(String[] args) {
        Head fcfsHead = new Head(0);
        FCFS fcfs = new FCFS(fcfsHead);
        SSTF sstf = new SSTF(new Head(0));
        SCAN scan = new SCAN(new Head(0));
        SCAN cscan = new CSCAN(new Head(0));

        try {
            fcfs.addRequest(new Request(1, 500));
            fcfs.addRequest(new Request(2, 502));
            fcfs.addRequest(new Request(3, 674));
            fcfs.addRequest(new Request(4, 542));
            fcfs.addRequest(new Request(5, 496));
            fcfs.addRequest(new Request(6, 510));
            fcfs.addRequest(new Request(7, 480));
            fcfs.addRequest(new Request(8, 414));
            fcfs.addRequest(new Request(9, 150));

            sstf.addRequest(new Request(1, 500));
            sstf.addRequest(new Request(2, 502));
            sstf.addRequest(new Request(3, 674));
            sstf.addRequest(new Request(4, 542));
            sstf.addRequest(new Request(5, 496));
            sstf.addRequest(new Request(6, 510));
            sstf.addRequest(new Request(7, 480));
            sstf.addRequest(new Request(8, 414));
            sstf.addRequest(new Request(9, 150));

            scan.addRequest(new Request(1, 500));
            scan.addRequest(new Request(2, 502));
            scan.addRequest(new Request(3, 674));
            scan.addRequest(new Request(4, 542));
            scan.addRequest(new Request(5, 496));
            scan.addRequest(new Request(6, 510));
            scan.addRequest(new Request(7, 480));
            scan.addRequest(new Request(8, 414));
            scan.addRequest(new Request(9, 150));
            
            cscan.addRequest(new Request(1, 500));
            cscan.addRequest(new Request(2, 502));
            cscan.addRequest(new Request(3, 674));
            cscan.addRequest(new Request(4, 542));
            cscan.addRequest(new Request(5, 496));
            cscan.addRequest(new Request(6, 510));
            cscan.addRequest(new Request(7, 480));
            cscan.addRequest(new Request(8, 414));
            cscan.addRequest(new Request(9, 150));

        } catch (OutOfDiscException e) {
            e.printStackTrace();
        }

        System.out.println("FCFS: " + fcfs.countAveragePath());
        System.out.println("SSTF: " + sstf.countAveragePath());
        System.out.println("SCAN: " + scan.countAveragePath());
        System.out.println("CSCAN: " + cscan.countAveragePath());

    }
}
