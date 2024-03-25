package employees.service;

public class JobMatcherResult {

    private int firstEmployee;
    private int secondEmployee;
    private int projectId;
    private long daysWorkedTogether;

    public JobMatcherResult(int firstEmployee, int secondEmployee, int projectId, long daysWorkedTogether) {
        this.firstEmployee = firstEmployee;
        this.secondEmployee = secondEmployee;
        this.projectId = projectId;
        this.daysWorkedTogether = daysWorkedTogether;
    }

    public int getFirstEmployee() {
        return firstEmployee;
    }

    public int getSecondEmployee() {
        return secondEmployee;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDaysWorkedTogether() {
        return daysWorkedTogether;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobMatcherResult result = (JobMatcherResult) o;

        if (getFirstEmployee() != result.getFirstEmployee()) return false;
        if (getSecondEmployee() != result.getSecondEmployee()) return false;
        if (getProjectId() != result.getProjectId()) return false;
        return getDaysWorkedTogether() == result.getDaysWorkedTogether();
    }

    @Override
    public int hashCode() {
        int result = getFirstEmployee();
        result = 31 * result + getSecondEmployee();
        result = 31 * result + getProjectId();
        result = 31 * result + (int) (getDaysWorkedTogether() ^ (getDaysWorkedTogether() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "JobMatcherResult{" +
                "firstEmployee=" + firstEmployee +
                ", secondEmployee=" + secondEmployee +
                ", projectId=" + projectId +
                ", daysWorkedTogether=" + daysWorkedTogether +
                '}';
    }

}
