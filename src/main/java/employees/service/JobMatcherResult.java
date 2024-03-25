package employees.service;

public class JobMatcherResult {

    private int firstEmployee;
    private int secondEmployee;
    private int projectId;
    private long daysWorkedTogether;

    JobMatcherResult(int firstEmployee, int secondEmployee, int projectId, long daysWorkedTogether) {
        this.firstEmployee = firstEmployee;
        this.secondEmployee = secondEmployee;
        this.projectId = projectId;
        this.daysWorkedTogether = daysWorkedTogether;
    }

    public static JobMatcherResultBuilder builder() {
        return new JobMatcherResultBuilder();
    }

    public int getFirstEmployee() {
        return this.firstEmployee;
    }

    public int getSecondEmployee() {
        return this.secondEmployee;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public long getDaysWorkedTogether() {
        return this.daysWorkedTogether;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JobMatcherResult)) return false;
        final JobMatcherResult other = (JobMatcherResult) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getFirstEmployee() != other.getFirstEmployee()) return false;
        if (this.getSecondEmployee() != other.getSecondEmployee()) return false;
        if (this.getProjectId() != other.getProjectId()) return false;
        if (this.getDaysWorkedTogether() != other.getDaysWorkedTogether()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof JobMatcherResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getFirstEmployee();
        result = result * PRIME + this.getSecondEmployee();
        result = result * PRIME + this.getProjectId();
        final long $daysWorkedTogether = this.getDaysWorkedTogether();
        result = result * PRIME + (int) ($daysWorkedTogether >>> 32 ^ $daysWorkedTogether);
        return result;
    }

    public String toString() {
        return "JobMatcherResult(firstEmployee=" + this.getFirstEmployee() + ", secondEmployee=" + this.getSecondEmployee() + ", projectId=" + this.getProjectId() + ", daysWorkedTogether=" + this.getDaysWorkedTogether() + ")";
    }

    public static class JobMatcherResultBuilder {
        private int firstEmployee;
        private int secondEmployee;
        private int projectId;
        private long daysWorkedTogether;

        JobMatcherResultBuilder() {
        }

        public JobMatcherResultBuilder firstEmployee(int firstEmployee) {
            this.firstEmployee = firstEmployee;
            return this;
        }

        public JobMatcherResultBuilder secondEmployee(int secondEmployee) {
            this.secondEmployee = secondEmployee;
            return this;
        }

        public JobMatcherResultBuilder projectId(int projectId) {
            this.projectId = projectId;
            return this;
        }

        public JobMatcherResultBuilder daysWorkedTogether(long daysWorkedTogether) {
            this.daysWorkedTogether = daysWorkedTogether;
            return this;
        }

        public JobMatcherResult build() {
            return new JobMatcherResult(this.firstEmployee, this.secondEmployee, this.projectId, this.daysWorkedTogether);
        }

        public String toString() {
            return "JobMatcherResult.JobMatcherResultBuilder(firstEmployee=" + this.firstEmployee + ", secondEmployee=" + this.secondEmployee + ", projectId=" + this.projectId + ", daysWorkedTogether=" + this.daysWorkedTogether + ")";
        }
    }
}
