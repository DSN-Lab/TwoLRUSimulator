package edu.ce.sharif.twolru;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by mohammad on 1/30/17.
 */
public class Simulator {


    private Long migToDram = 0L;
    private Long dramRead;
    private Long dramWrite;
    private Long pcmRead;
    private Long pcmWrite;
    private Long migToPcm = 0L;

    private final Long DRAM_SIZE;
    private final Long PCM_SIZE;
    private final Long PCM_THRESHOLD;



    public Simulator(Long dramSize, Long pcmSize, Long pcmThreshold)
    {
        this.DRAM_SIZE = dramSize;
        this.PCM_SIZE = pcmSize;
        this.PCM_THRESHOLD = pcmThreshold;
    }

    private List<Page> dram = new ArrayList<Page>(){
        @Override
        public boolean add(Page page) {
            if(this.size()==DRAM_SIZE) {
                migToPcm++;
                pcm.add(dram.get(0));
                dram.remove(0);
                dram.add(page);
                return false;
            }
            boolean ret= super.add(page);
            Collections.sort(this);
            return ret;
        }
    };

    private List<Page> pcm = new ArrayList<Page>(){
        @Override
        public boolean add(Page page) {
            if(this.size()==PCM_SIZE) {
                pcm.remove(0);
                pcm.add(page);
                return false;
            }
            boolean ret= super.add(page);
         //  Collections.sort(this);
            return ret;
        }
    };


    public void printStatistics(){
        System.out.println("migration to dram: "+migToDram);
        System.out.println("migration to pcm: "+ migToPcm);
    }

    public int add(Page newPage)
    {
        int dramIndex=dram.indexOf(newPage);
        if(dramIndex!=-1) {
            Page p=new Page( dram.get(dramIndex).getAddress());
            p.setAccessNum(dram.get(dramIndex).getAccessNum()+1);

            dram.remove(dramIndex);
            dram.add(p);
            //dram.get(dramIndex).setAccessNum(dram.get(dramIndex).getAccessNum()+1);
        }
        //else
        //    dram.add(newPage);
        else {
            int pcmIndex = pcm.indexOf(newPage);
            if(pcmIndex != -1)
            {
                pcm.get(pcmIndex).setAccessNum(pcm.get(pcmIndex).getAccessNum()+1);
                Page pcm2=pcm.get(pcmIndex);
                if(pcm.get(pcmIndex).getAccessNum() >= PCM_THRESHOLD) {
                    migToDram++;
                    Page p=new Page(pcm.get(pcmIndex).getAddress());
                    p.setAccessNum(pcm.get(pcmIndex).getAccessNum());
                    pcm.remove(pcmIndex);
                    dram.add(p);
                }

            }
            else{
                dram.add(newPage);
            }
        }
        return 0;
    }

}
